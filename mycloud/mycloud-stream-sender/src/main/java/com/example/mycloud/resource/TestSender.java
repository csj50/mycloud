package com.example.mycloud.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mycloud.bean.Product;

@RestController
public class TestSender {

	@Autowired
	private ISendService sendService;
	
	@RequestMapping("/send")
	public void send() {
		String msg = "abc..........";
		Message message = MessageBuilder.withPayload(msg.getBytes()).build();
		sendService.send().send(message);
	}
	
	//消息分组
	@RequestMapping("/send2")
	public void send2() {
		Product product = new Product();
		product.setId("1");
		product.setName("abc");
		Message message = MessageBuilder.withPayload(product).build();
		sendService.send().send(message);
	}
}
