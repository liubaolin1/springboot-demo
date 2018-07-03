## 这个是用springboot简单搭建了一个模块化的项目 ##
### 项目结构 ###
* appmodule 项目主启动入口模块
* module1 模块1 有一个链接数据库的查询方法
* module2 模块2 自己的一些练习
* utils 自己封装的一些工具类

#### 集成mybatis,mysql ####
+ swagger2 入口：http://localhost:8999/swagger-ui.html
 ![image](https://github.com/liubaolin1/springboot-demo/blob/master/utils/src/main/java/com/example/utils/image/swagger2.jpg)
+ druid 监控入口：http://localhost:8999/druid/login.html 账号密码：admin
 ![image](https://github.com/liubaolin1/springboot-demo/blob/master/utils/src/main/java/com/example/utils/image/druid.jpg)
+ druid 参考 https://blog.csdn.net/CoffeeAndIce/article/details/78707819
+ 控制台打印sql
#### 自动生成代码工具 ####
+ generatorConfig.xml 中配置
#### 问题 ####
+ 这种模块化的不好打包
#### 更新日志 ####
#### 2018-06-15 ####
+ 主模块：新增加一个定时任务 Timetask类 去掉注解注释启动项目启动定时任务 
+ module1模块：新增利用poi导出excel功能 浏览器输入 http://localhost:8999/method3 直接下载 
#### 2018-06-20  ####
+ 引入redis 新增reids工具类
+ 在StringUtils中引入一些校验方法

### 学不可以已 ###

#### 2018-07-02  ####
+ 在mdule2模块中加入了自己的一些练习 
+ 主要写的是反射机制

#### 2018-07-03  ####
+ 今天发现druid的管理页面打不开了 找各种原因 后来发现主启动类中的 注解改动之后未测试是否有影响druid
+ 元组的使用方法
+ 设置多个配置文件，默认启用application-test.properties 这个配置文件主要是解决不同环境启用不同配置文件的问题 详见模块2