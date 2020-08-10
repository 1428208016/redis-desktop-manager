package com.lingzhen.myproject.enlightenmentstar.filter;

import javax.servlet.*;
import java.io.IOException;

public class TestFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("----过滤器初始化----");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("1");
	}

	@Override
	public void destroy() {
		
	}

}
