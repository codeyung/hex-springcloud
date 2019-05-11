package com.hex.code.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.14:10
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableTurbine
@EnableHystrixDashboard
@EnableAdminServer
// 动态刷新
//@RefreshScope
public class MonitorApplication8020 {

//    仪表盘熔断监控 http://192.168.1.100:8020/hystrix
//    http://192.168.1.100:8001/goods 服务调用测试
//    http://192.168.1.100:8001/actuator/hystrix.stream 查看 单体
//    http://localhost:8020/turbine.stream   集群监控


    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication8020.class, args);
    }

}
