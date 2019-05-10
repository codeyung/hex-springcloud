package com.hex.code.user;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaClient
//@EnableFeignClients(basePackages = {"com.hex.code"})
@ComponentScan(basePackages = {"com.hex.code"})
public class UserApplication8001 {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication8001.class, args);
    }

}
