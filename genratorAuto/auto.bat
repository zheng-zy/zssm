@echo off
echo [信息] 生成mybatis文件。
pause
java -jar ./mybatis-generator-core-1.3.2.jar -configfile generatorConfig.xml -overwrite
pause