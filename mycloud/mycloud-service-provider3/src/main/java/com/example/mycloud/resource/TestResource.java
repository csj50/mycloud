package com.example.mycloud.resource;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mycloud.service.TestHystrixService;

@RestController
public class TestResource {

//	@Autowired
//	DiscoveryClient discoveryClient; // 服务发现客户端

	@Autowired
	private Registration registration; // 服务注册
	
	@Autowired
	private TestHystrixService testHystrixService;

	@RequestMapping("/InstanceInfo")
	public String Info() {
		String serviceId = registration.getServiceId();
		System.out.println("服务id为: " + serviceId);
		return registration.getInstanceId();
	}
	
	@RequestMapping("/testHystrix1")
	public String testHystrix1() {
		return testHystrixService.testHystrix1();
	}
	
	@RequestMapping("/get")
	public String get(Integer id) {
		return testHystrixService.get(id);
	}
	
	@RequestMapping("/del")
	public void del(Integer id) {
		testHystrixService.del(id);
	}

	@RequestMapping("/testHystrix3")
	public void testHystrix3() throws InterruptedException, ExecutionException {
		Future<String> s1 = testHystrixService.getIdInfo(1);
		Future<String> s2 = testHystrixService.getIdInfo(2);
		Future<String> s3 = testHystrixService.getIdInfo(3);
		System.out.println(s1.get().toString());
		System.out.println(s2.get().toString());
		System.out.println(s3.get().toString());
	}
	
	@RequestMapping("/testHystrix4")
	public String testHystrix4() {
		return testHystrixService.testHystrix4();
	}
	
	@RequestMapping("/testHystrix5")
	public String testHystrix5() {
		return testHystrixService.testHystrix5();
	}
	
	@RequestMapping("/testHystrix6")
	public String testHystrix6() {
		return testHystrixService.testHystrix6();
	}
}
