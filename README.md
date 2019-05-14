# hex
基于SpringCloud构建的微服务 基础版本

> 组件说明：
>     
|组件名称|版本|描述|
|-|-|-|
| SpringBoot|2.1.4|基础框架|
| Spring Cloud|Greenwich.SR1|基础框架|
| Spring Cloud Gateway|-|网关|
| Spring Cloud Eureka|-|注册中心|
| Spring Cloud Ribbon|-|客户端负载均衡|
| Spring Cloud OpenFeign|-|内部服务调用|
| Spring Cloud Hystrix|-|熔断|
| Spring Boot Admin|-|程序监控|
| Spring Cloud Config|-|配置中心 暂时本地|
| Spring Cloud Sleuth|-|服务链跟踪|
| Redis|单机/集群/阿里|缓存|
| MySQL|单机/集群/阿里RDS|数据库集群|
>
|待实现|打算使用|描述|
|-|-|-|
| 分布式事物 |tx-lcn| 已用 tx-manager 简单实现了一下|
| 分布式锁 |-| 准备用redis试下|
| 分布式ID |-| 试试美团Leaf or snowflake|
| Kafka|消息/日志/ELK|-|
| 鉴权|新版本总觉得有问题|没有用Aouth2.0 暂时就在网关过滤了一下|
| 部署|就暂时做了Dockerfile|自己机器有点渣 后面再搞 docker compose|

> 一些问题：  
新版的鉴权感觉有些奇怪 我做的服务帐号也没有统一 所以我暂时只是使用了JWT 在网关做过滤和基本的认证  
认证过后服务内部调用基本信任
需要特别实现则可以在JWT的token中加入自定义的权限描述  
在后端服务调用阶段做验证  

> 没有用Dubbo是因为个人用过较多技术是SpringBoot那一体系比较熟悉、  而且RPC和REST各有好处 因人而异   
> 积累的有些业务太相关不能放出来 只能自己写着玩 整理一下  希望有老哥们来交流~

启动顺序
eureka-service
config-service
tx-manager
user-service
mall-service
order-service
gateway-service    开启后可以看路由文件统一走网关访问 写了简单的过滤规则与日志计时

monitor-service 需要再开

本地测试
host  可以不用改用 locahost
127.0.0.1 eureka-service  
127.0.0.1 monitor-service  
127.0.0.1 config-service  
127.0.0.1 user-service  
127.0.0.1 mall-service  
127.0.0.1 order-service  

网关地址
http://gateway-service:8000/
注册中心地址
http://eureka-service:8010/
监控地址
http://monitor-service:8020/
配置中心
http://config-service:8030/
用户服务
http://user-service:8001/
商品服务
http://mall-service:8002/
订单服务
http://order-service:8003/
tx-manager服务
http://tx-manager:8050/    密码codingapi
  
http://monitor-service:8020/hystrix  仪表盘熔断监控  
http://monitor-service:8001/actuator/hystrix.stream 查看 单体  
http://monitor-service:8020/turbine.stream   集群监控  
http://monitor-service:9411 链式监控 java -jar zipkin.jar  
  
config 配置 默认 native  
http://config-service:8030/mall-service-native.yml  
http://monitor-service:8020  admin 监控  
http://monitor-service:8020/hystrix   熔断监控 输入参数 http://monitor-service:8020/turbine.stream  


  
  
一个本地redis与三个库之前做测试用的有些麻烦QAQ
  
用户数据库: 
  
CREATE DATABASE user
  
CREATE TABLE `user` (
  `user_id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `phone_number` varchar(18) NOT NULL DEFAULT '' COMMENT '手机号',
  `password` varchar(100) NOT NULL DEFAULT '' COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '注册时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `uk_phone` (`phone_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
  
商品数据库:
   
CREATE DATABASE order
  
CREATE TABLE `goods` (
  `goods_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `goods_name` varchar(200) NOT NULL DEFAULT '' COMMENT '商品名称',
  `goods_desc` varchar(200) NOT NULL DEFAULT '' COMMENT '商品描述',
  `stock` int(11) NOT NULL COMMENT '商品库存',
  `goods_status` tinyint(4) NOT NULL COMMENT '上架/下架 1/0',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
  
订单数据库: 
  
CREATE DATABASE order;
  
CREATE TABLE `order` (
  `order_id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `goods_id` int(11) NOT NULL COMMENT '商品id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
  
tx事物数据库: 
  
CREATE DATABASE tx-manager;
  
CREATE TABLE `t_tx_exception` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) DEFAULT NULL,
  `unit_id` varchar(32) DEFAULT NULL,
  `mod_id` varchar(128) DEFAULT NULL,
  `transaction_state` tinyint(4) DEFAULT NULL,
  `registrar` tinyint(4) DEFAULT NULL,
  `remark` varchar(4096) DEFAULT NULL,
  `ex_state` tinyint(4) DEFAULT NULL COMMENT '0 未解决 1已解决',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC; 
        
