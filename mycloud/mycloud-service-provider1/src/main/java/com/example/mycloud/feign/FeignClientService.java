package com.example.mycloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mycloud.hystrix.FeignClientServiceFallback;
import com.example.mycloud.hystrix.FeignClientServiceFallbackFactory;

@FeignClient(name = "inside-app1", fallback = FeignClientServiceFallback.class, 
    fallbackFactory = FeignClientServiceFallbackFactory.class) //fallback和fallbackFactory配置一个
public interface FeignClientService {
	
	@GetMapping("/insideApp1/remoteApi/{id}")
	public String remoteApi(@PathVariable String id);
}
