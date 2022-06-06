package com.example.mycloud.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Future;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.conf.HystrixPropertiesManager;

@CacheConfig(cacheNames = {"com.example.mycloud"})
@Service
public class TestHystrixService {

	/********************
	 * 服务降级案例
	 ********************/
	
	@HystrixCommand(fallbackMethod = "fallback", //降级方法
			//配置参数
			commandProperties = {
					//默认10秒内，如果并发数达到该设置值，请求会被拒绝和抛出异常并且fallback不会被调用
					@HystrixProperty(name=HystrixPropertiesManager.FALLBACK_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value="15")
			})
	public String testHystrix1() {
		throw new RuntimeException("服务异常");
		//return "正常请求......";
	}
	
	/**
	 * 返回托底数据
	 * @return
	 */
	public String fallback() {
		return "fallback method......";
	}
	
	/********************
	 * 请求缓存案例
	 ********************/
	
	@Cacheable(key = "'get' + #id")
	public String get(Integer id) {
		System.out.println("==========get==========");
		return "测试缓存..." + id;
	}
	
	@CacheEvict(key = "'get' + #id")
	public void del(Integer id) {
		System.out.println("==========del==========");
	}
	
	/********************
	 * 请求合并案例
	 ********************/
	
	@HystrixCollapser(batchMethod = "batchIdInfo",
			scope = com.netflix.hystrix.HystrixCollapser.Scope.GLOBAL,
			collapserProperties = {
					//请求时间间隔在50ms之内的请求会被合并为一个请求
					@HystrixProperty(name = "timerDelayInMilliseconds", value = "20"),
					//设置触发批处理执行之前，在批处理中允许的最大请求数
					@HystrixProperty(name = "maxRequestsInBatch", value = "200")
			}
			)
	public Future<String> getIdInfo(Integer id) {
		//没有打印这里，因为请求合并了
		System.out.println("==========" + id + "==========");
		return null;
	}
	
	@HystrixCommand
	public List<String> batchIdInfo(List<Integer> id) {
		List<String> list = new ArrayList<>();
		for(Integer i : id) {
			list.add("id: " + i);
		}
		return list;
	}
	
	/********************
	 * 服务熔断案例
	 ********************/
	
	@HystrixCommand(fallbackMethod = "fallback2",
			commandProperties = {
					//默认20个；10s内请求数大于20个时就启动熔断器，当请求符合熔断条件时将触发getFallback()
					@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_REQUEST_VOLUME_THRESHOLD, value = "10"),
					//请求错误率大于50%时就熔断，然后for循环发起请求，当请求符合熔断条件时将触发getFallback()
					@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_ERROR_THRESHOLD_PERCENTAGE, value = "50"),
					//默认5秒；熔断多少秒后去尝试请求
					@HystrixProperty(name = HystrixPropertiesManager.CIRCUIT_BREAKER_SLEEP_WINDOW_IN_MILLISECONDS, value = "5000")
			}
			)
	public String testHystrix4() {
		Random random = new Random();
		int i = random.nextInt();
		if (i % 2 == 1) {
			throw new RuntimeException("服务异常");
		} 
		return "正常请求......";
	}
	
	public String fallback2() {
		return "fallback2 method......";
	}
	
	/********************
	 * 线程池隔离案例
	 ********************/
	
	@HystrixCommand(groupKey = "service-provider1", commandKey = "testHystrix5",
			threadPoolKey = "testHystrix5",
			threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "30"), //线程池大小
					@HystrixProperty(name = "maxQueueSize", value = "100"), //最大队列长度
					@HystrixProperty(name = "keepAliveTimeMinutes", value = "2"), //线程存活时间
					@HystrixProperty(name = "queueSizeRejectionThreshold", value = "15") //拒绝请求
			},
			fallbackMethod = "falback3"
			)
	public String testHystrix5() {
		return "正常请求......";
	}
	
	public String fallback3() {
		return "fallback3 method......";
	}
	
	/********************
	 * 信号量隔离案例
	 ********************/
	
	@HystrixCommand(fallbackMethod = "fallback4",
			commandProperties = {
					@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_STRATEGY, value = "SEMAPHORE"), //信号量隔离
					@HystrixProperty(name = HystrixPropertiesManager.EXECUTION_ISOLATION_SEMAPHORE_MAX_CONCURRENT_REQUESTS, value = "100") //信号量最大并发数
			}
			)
	public String testHystrix6() {
		return "正常请求......";
	}
	
	public String fallback4() {
		return "fallback4 method......";
	}
}
