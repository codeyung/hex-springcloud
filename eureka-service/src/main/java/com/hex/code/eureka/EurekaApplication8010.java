package com.hex.code.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-10.14:10
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaServer
public class EurekaApplication8010 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaApplication8010.class, args);
    }

}
