package com.example.mycloud.run;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import zipkin2.server.internal.EnableZipkinServer;

@EnableZipkinServer
@SpringBootApplication
@ComponentScan("com.example.mycloud")
public class ZipkinServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkinServerApplication.class, args);
	}
}
