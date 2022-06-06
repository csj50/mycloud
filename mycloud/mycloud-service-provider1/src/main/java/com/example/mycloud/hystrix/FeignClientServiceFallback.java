package com.example.mycloud.hystrix;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mycloud.feign.FeignClientService;

@Component
public class FeignClientServiceFallback implements FeignClientService {

	@Override
	public String remoteApi(@PathVariable String id) {
		return "fallback......";
	}

}
