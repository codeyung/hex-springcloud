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
| Spring Cloud Sleuth|-|服务链跟踪|
| Redis|单机/集群/阿里|缓存|
| MySQL|单机/集群/阿里RDS|数据库集群|
>
|待实现|打算使用|描述|
|-|-|-|
| 配置中心|Spring Cloud Config|暂时本地|
| 分布式事物|LCN/自己搞个简单的玩一下|-|
| Kafka|消息/日志/ELK|-|
| 鉴权|新版本总觉得有问题|没有用Aouth2.0 打算用网关加过滤试一下|

> 一些问题：  
新版的鉴权感觉有些奇怪 我做的服务帐号也没有统一 所以我暂时只是使用了JWT 在网关做过滤和基本的认证  
认证过后服务内部调用基本信任
需要特别实现则可以在JWT的token中加入自定义的权限描述  
在后端服务调用阶段做验证  

> 没有用Dubbo是因为个人用过较多技术是SpringBoot那一体系比较熟悉、  而且RPC和REST各有好处 因人而异   
> 积累的有些业务太相关不能放出来 只能自己写着玩 整理一下  希望有老哥们来交流~

<!-- > ## 这是一个标题。
>
> 1.   这是第一行列表项。
> 2.   这是第二行列表项。 -->










host  
127.0.0.1 eureka-service  
127.0.0.1 monitor-service  
127.0.0.1 config-service  
127.0.0.1 user-service  
127.0.0.1 mall-service  
127.0.0.1 order-service  


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

http://user-service:8001/goods 查看商品 feign调用  
http://mall-service:8002/goods 商品服务自身调用  
http://order-service:8003/order 添加订单  

http://monitor-service:8020/hystrix  仪表盘熔断监控  
http://monitor-service:8001/goods 服务调用测试  
http://monitor-service:8001/actuator/hystrix.stream 查看 单体  
http://monitor-service:8020/turbine.stream   集群监控  
http://monitor-service:9411 链式监控 java -jar zipkin.jar  

config 配置 默认 native  
http://config-service:8030/mall-service-native.yml  
http://monitor-service:8020  admin 监控  
http://monitor-service:8020/hystrix   熔断监控 输入参数 http://monitor-service:8020/turbine.stream  

启动顺序  
eureka-service  
config-service  
user-service  
mall-service  
order-service  

monitor-service  
