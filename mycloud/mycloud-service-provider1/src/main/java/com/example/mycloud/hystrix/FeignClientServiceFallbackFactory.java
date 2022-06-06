package com.example.mycloud.hystrix;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.mycloud.feign.FeignClientService;

import feign.hystrix.FallbackFactory;

public class FeignClientServiceFallbackFactory implements FallbackFactory<FeignClientService> {

	private Logger logger = LoggerFactory.getLogger(FeignClientServiceFallbackFactory.class);
	
	@Override
	public FeignClientService create(final Throwable cause) {
		return new FeignClientService() {

			@Override
			public String remoteApi(String id) {
				logger.warn("fallback exception: ", cause);
				return "fallback......";
			}
			
		};
	}

}
