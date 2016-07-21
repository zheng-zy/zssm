package com.base;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;
import static org.elasticsearch.index.query.QueryBuilders.*;

import java.net.InetAddress;
import java.util.Date;

import org.apache.log4j.Logger;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.sort.SortOrder;
import org.elasticsearch.search.sort.SortParseElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @desc
 * @author zhengzy
 * @version 2016年7月20日
 */
public class TestElasticSearch {
	
	private static Logger logger = Logger.getLogger(TestElasticSearch.class);

	private Node node;

	private Client client;

	@Before
	public void setUp() throws Exception {
		// 1.节点客户端,加入到集群
		// on startup
		// node = nodeBuilder().clusterName("elasticsearch").node();
		// client = node.client();
		// 当前节点是否需要保存数据
		// node = nodeBuilder().client(true).node();
		// client = node.client();

		// 传输（transport）客户端,不加入到集群，可以动态添加删除客户端，刷新间隔5s
		// 如果你所使用的节点名不是elasticsearch，那么可以通过setting设置,也可以通过配置文件配置
		// https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/transport-client.html
		Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();
		client = TransportClient.builder().settings(settings).build()
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
	}

	@After
	public void tearDown() throws Exception {
		// 1.on shutdown
		// node.close();

		// 2.on shutdown
		client.close();
	}

	@Test
	public void query() throws Exception {
		SearchRequestBuilder builder = client.prepareSearch("bank").setTypes("account")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				//
//				.setQuery(QueryBuilders.termQuery("firstname", "Branch")) // Query
				.setQuery(QueryBuilders.wildcardQuery("firstname", "branch")) // Query
//				.setPostFilter(QueryBuilders.rangeQuery("account_number").from(0).to(2)) // Filter
				.setFrom(0).setSize(10).setExplain(true);
		logger.info(builder.toString());
		SearchResponse response = builder.execute().actionGet();
		logger.info(response.toString());
//		logger.info(response.getHits().getAt(0).getSource().get("firstname"));
		SearchHits hits = response.getHits();
		long num = response.getHits().getTotalHits();
		logger.info("total num:"+num);
		for (long i=0; i<num;i++){ 
			logger.info(hits.getAt((int) i).getSource().get("firstname"));
			logger.info(hits.getAt((int) i).getSource().get("lastname"));
		}
	}

	public void test() throws Exception {
		// 创建索引,过滤器(略)
		IndexResponse response = client
				.prepareIndex("twitter", "tweet", "1").setSource(jsonBuilder().startObject().field("user", "kimchy")
						.field("postDate", new Date()).field("message", "trying out Elasticsearch").endObject())
				.execute().actionGet();
		// 获取
		GetResponse response2 = client.prepareGet("twitter", "tweet", "1")
				// operationThreaded设置为true表示操作执行在不同的线程上面
				.setOperationThreaded(false).execute().actionGet();
		// 删除
		// DeleteResponse response3 = client.prepareDelete("twitter", "tweet",
		// "1")
		// .execute().actionGet();
		// 修改
		client.prepareUpdate("twitter", "tweet", "1")
				.setDoc(jsonBuilder().startObject().field("gender", "male").endObject()).get();

		// java API也支持使用upsert。如果文档还不存在，会根据upsert内容创建一个新的索引(略)

		// 批量
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		bulkRequest.add(client.prepareIndex("twitter", "tweet", "1")
				.setSource(jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
						.field("message", "trying out Elasticsearch").endObject()));
		bulkRequest.add(client.prepareIndex("twitter", "tweet", "2").setSource(jsonBuilder().startObject()
				.field("user", "kimchy").field("postDate", new Date()).field("message", "another post").endObject()));
		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// process failures by iterating through each bulk response item
		}

		// 查询
		SearchResponse response4 = client.prepareSearch("index1", "index2").setTypes("type1", "type2")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				//
				.setQuery(QueryBuilders.termQuery("multi", "test")) // Query
				.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18)) // Filter
				.setFrom(0).setSize(60).setExplain(true).execute().actionGet();

		// 滚动API可以用来检索大量的结果
		QueryBuilder qb = termsQuery("multi", "test");

		SearchResponse scrollResp = client.prepareSearch("test").addSort(SortParseElement.DOC_FIELD_NAME, SortOrder.ASC)
				.setScroll(new TimeValue(60000)).setQuery(qb).setSize(100).execute().actionGet();
		// 100 hits per shard will be returned for each scroll
		// Scroll until no hits are returned
		while (true) {

			for (SearchHit hit : scrollResp.getHits().getHits()) {
				// Handle the hit...
			}
			scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute()
					.actionGet();
			// Break condition: No hits are returned
			if (scrollResp.getHits().getHits().length == 0) {
				break;
			}
		}

		// 多客户端,聚合
		// https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-msearch.html
	}

}
