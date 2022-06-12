package com.example.mycloud.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.ComponentScan;

import com.example.mycloud.resource.ISendService;

@EnableBinding({ISendService.class}) //把发送接口绑定进来
@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan("com.example.mycloud")
public class StreamSenderApplication {
	
	public static void main(String[] args) {
        SpringApplication.run(StreamSenderApplication.class, args);
    }
}
