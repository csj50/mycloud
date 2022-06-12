package com.example.mycloud.service;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;

import com.example.mycloud.bean.Product;
import com.example.mycloud.resource.IReceiveService;

@Service
@EnableBinding(IReceiveService.class) //绑定
public class ReceiveService {

	@StreamListener("stream-exchange")
	public void onReceive(byte[] msg) {
		System.out.println("receive: " + new String(msg));
	}
	
	//消息分组
	@StreamListener(IReceiveService.INPUT)
	public void onReceive2(Product obj) {
		System.out.println("receive: " + obj.toString());
	}
}
