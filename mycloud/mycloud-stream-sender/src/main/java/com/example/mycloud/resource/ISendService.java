package com.example.mycloud.resource;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

/**
 * 发送的接口
 * @author user
 *
 */
public interface ISendService {

	@Output("stream-exchange")
	public SubscribableChannel send();
	
	//消息分组
	String OUTPUT = "groupOutput";
	
	@Output(ISendService.OUTPUT)
	public SubscribableChannel send2();
	
}
