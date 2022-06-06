package com.example.mycloud.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@EnableTurbine
@EnableHystrix
@EnableHystrixDashboard
@EnableCaching  //加入springcache
@EnableCircuitBreaker  //开启服务降级-断路器
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.example.mycloud")
@EnableFeignClients("com.example.mycloud")
public class ServiceProvider3Application {
	public static void main(String[] args) {
        SpringApplication.run(ServiceProvider3Application.class, args);
    }
}
