package com.hex.code.order;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = {"com.hex.code"})
@ComponentScan(basePackages = {"com.hex.code"})
@EnableCircuitBreaker   //支持监控
//@EnableHystrixDashboard //熔断器的可视化仪表盘界面
public class OrderApplication {


//    /**
//     * 文字版熔断监控 /hystrix.stream
//     *
//     * @return
//     */
//    @Bean
//    public ServletRegistrationBean getServlet() {
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

}
