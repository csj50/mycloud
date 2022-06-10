package com.example.mycloud.run;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class LogFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	public boolean shouldFilter() {
		//开启过滤器，设置为true
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//过滤器的内容，打印请求的信息
		RequestContext rc = RequestContext.getCurrentContext();
		HttpServletRequest request = rc.getRequest();
		logger.info("method={},url={}", request.getMethod(), request.getRequestURL().toString());
		
		//权限过滤例子
//		String token = request.getParameter("token");
//		if (token == null) {
//			logger.warn("token is null..........");
//			rc.setSendZuulResponse(false); //代表结束请求，不在继续下级传递
//			rc.setResponseStatusCode(401);
//			rc.setResponseBody("{\"result\":\"token is null\"}");
//			rc.getResponse().setContentType("text/html;charset=utf-8");
//		} else {
//			//校验token，redis验证
//			logger.info("token is ok");
//		}
		
		//异常过滤器例子
//		throw new RuntimeException();
		
		return null;
	}

	@Override
	public String filterType() {
		//过滤器的类型
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
