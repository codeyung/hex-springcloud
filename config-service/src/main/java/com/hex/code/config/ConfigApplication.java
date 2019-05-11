package com.hex.code.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: codeyung  E-mail:yjc199308@gmail.com
 * @date: 2019-05-11.13:23
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class ConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}