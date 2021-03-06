package com.example.mycloud.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.example.mycloud")
@EnableFeignClients("com.example.mycloud")
public class ServiceProvider1Application {
	public static void main(String[] args) {
        SpringApplication.run(ServiceProvider1Application.class, args);
    }
}
