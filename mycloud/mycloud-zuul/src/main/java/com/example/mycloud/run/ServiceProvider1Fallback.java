package com.example.mycloud.run;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class ServiceProvider1Fallback implements FallbackProvider {

	@Override
	public String getRoute() {
		//代表为哪个服务fallback
		return "service-provider1";
	}

	@Override
	public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				//返回的文本信息
				String input = "服务不可用，请联系管理员！";
				return new ByteArrayInputStream(input.getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				//http头部类型
				HttpHeaders header = new HttpHeaders();
				MediaType mt = new MediaType("application","json",Charset.forName("utf-8"));
				header.setContentType(mt);
				return header;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				//http状态码
				return HttpStatus.OK;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				//http状态值
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				//http状态文本
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub
				
			}
			
		};
	}

}
