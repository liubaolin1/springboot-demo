##这个是用springboot简单搭建了一个模块化的项目
###项目结构
* appmodule 项目主启动入口模块
* module1 模块1 有一个链接数据库的查询方法
* module2 模块2 空模块
* utils 自己封装的一些工具类

####集成mybatis,mysql
* swagger2 入口：http://localhost:8999/swagger-ui.html
* druid 监控入口：http://localhost:8999/druid/login.html 账号密码：admin
* 控制台会输出sql

####自动生成代码工具
* generatorConfig.xml 中配置

**更新日志**
2018-06-15 09：21
主模块：新增加一个定时任务 Timetask类 去掉注解注释启动项目启动定时任务 
module1模块：新增利用poi导出excel功能 浏览器输入 http://localhost:8999/method3 直接下载 