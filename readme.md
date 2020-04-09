###cloud项目各模块说明

###1.api用于管理全部模块用到的实体类和Feign（服务之间调用使用到的框架）

###2.core为支撑所有项目的核心工具类和系统封装类

###3.java-codegenerator是使用框架mybatis-plus的代码生成器

###4.config-server作为项目配置服务器，可通过git仓库进行远程替换*.yml文件（代码托管使用github，地址为：https://github.com/huatou/cloud-config.git）

###5.dashboard用于监控各个模块的运行情况

###6.eureka为整个微服务架构的核心，是各个服务之间的桥梁（端口号为7100，8100，9100）

###7.hadoop为大数据框架，HBASE为数据库，hdfs为文件存储系统，一般都是服务于大型数据

###8.kafka，数据总线，用于服务之间的数据传输

###9.OAuth2，为服务提供权限认证

###9.rabbitMQ，类似于kafka的数据总线，用于服务之间的数据传输

###9.zuul，网关，为服务集中请求控制，通过路由访问。

###10.file,user为测试模块