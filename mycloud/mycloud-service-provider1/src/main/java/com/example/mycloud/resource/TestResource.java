package com.example.mycloud.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestResource {

//	@Autowired
//	DiscoveryClient discoveryClient; // 服务发现客户端

	@Autowired
	private Registration registration; // 服务注册

	@RequestMapping("/InstanceInfo")
	public String Info() {
		String serviceId = registration.getServiceId();
		System.out.println("服务id为: " + serviceId);
		return registration.getInstanceId();
	}

}
