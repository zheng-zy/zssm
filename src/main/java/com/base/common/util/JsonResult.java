package com.base.common.util;
/** 
* @desc 响应对象
* @author zhengzy 
* @version 2016年4月26日
*/
public class JsonResult {

    private static final String OK = "ok";
    private static final String ERROR = "error";

    private Meta meta;
    private Object data;

    public JsonResult() {
		// TODO Auto-generated constructor stub
	}
    
    public JsonResult success() {
        this.meta = new Meta(true, OK);
        return this;
    }

    public JsonResult success(Object data) {
        this.meta = new Meta(true, OK);
        this.data = data;
        return this;
    }

    public JsonResult failure() {
        this.meta = new Meta(false, ERROR);
        return this;
    }

    public JsonResult failure(String message) {
        this.meta = new Meta(false, message);
        return this;
    }

    public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}



	public class Meta {

        private boolean success;
        private String message;

        public Meta() {
			// TODO Auto-generated constructor stub
		}
        
        public Meta(boolean success) {
            this.success = success;
        }

        public Meta(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

		public boolean isSuccess() {
			return success;
		}

		public void setSuccess(boolean success) {
			this.success = success;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

    }
}