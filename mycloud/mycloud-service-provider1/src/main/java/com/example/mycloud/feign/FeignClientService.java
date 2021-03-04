package com.example.mycloud.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "inside-app1")
public interface FeignClientService {
	
	@GetMapping("/insideApp1/remoteApi/{id}")
	public String remoteApi(@PathVariable String id);
}
