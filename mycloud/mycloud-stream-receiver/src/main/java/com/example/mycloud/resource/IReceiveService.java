package com.example.mycloud.resource;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * 接收的接口
 * @author user
 *
 */
public interface IReceiveService {

	@Input("stream-exchange")
	public SubscribableChannel receive();
	
	//消息分组
	String INPUT = "groupInput";
	
	@Input(IReceiveService.INPUT)
	public SubscribableChannel receive2();
}
