package com.example.mycloud.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.mycloud.feign.FeignClientService;

@RestController
public class FeignTestResource {

	@Autowired
	FeignClientService feignClientService;
	
	@GetMapping("/feignTest/{id}")
	public String feignTest(@PathVariable String id) {
		return feignClientService.remoteApi(id);
	}
}
