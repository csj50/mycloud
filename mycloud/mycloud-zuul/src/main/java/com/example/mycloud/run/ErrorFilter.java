package com.example.mycloud.run;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

@Component
public class ErrorFilter extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(ErrorFilter.class);
	
	@Override
	public boolean shouldFilter() {
		//开启过滤器，设置为true
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		//过滤器的内容，打印请求的信息
		//RequestContext rc = RequestContext.getCurrentContext();
		//HttpServletRequest request = rc.getRequest();
		logger.info("--------------------error--------------------");
		
		return null;
	}

	@Override
	public String filterType() {
		//过滤器的类型
		return "error";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
