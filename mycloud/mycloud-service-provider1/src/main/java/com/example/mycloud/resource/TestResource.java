package com.example.mycloud.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;

@RestController
public class TestResource {

//	@Autowired
//	DiscoveryClient discoveryClient; // 服务发现客户端

	@Autowired
	private Registration registration; // 服务注册

	@Value("${hello:未取值}")
    private String testValue;
	
	@ApolloConfig("application") 
	private Config propConfig; // 注入默认的名称空间配置
	
	@ApolloConfig("application.yml")
	private Config ymlConfig; // 注入指定的名称空间配置
	
	@RequestMapping("/InstanceInfo")
	public String Info() {
		String serviceId = registration.getServiceId();
		System.out.println("服务id为: " + serviceId);
		
		return registration.getInstanceId();
	}
	
	@RequestMapping("/ApolloTest")
	public String ApolloTest() {
		System.out.println("testValue: " + testValue);
		return testValue;
	}

	@RequestMapping("/ApolloTestYml")
	public String ApolloTestYml() {
		Config config = ConfigService.getConfig("application.yml");
		String someKey = "respCode.000000";
		String someDefaultValue = "未定义";
		String value = config.getProperty(someKey, someDefaultValue);
		System.out.println("value: " + value);
		return value;
	}
}
