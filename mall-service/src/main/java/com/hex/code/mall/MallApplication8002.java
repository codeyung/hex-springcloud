package com.hex.code.mall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.12:56
 */

@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = {"com.hex.code"})
public class MallApplication8002 {


    public static void main(String[] args) {
        SpringApplication.run(MallApplication8002.class, args);
    }

}
