package com.born.bc.commons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * request请求头filter
 * 
 * @author wangjian
 *
 */
public class HeaderFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpServletRequest requset = (HttpServletRequest) req;

		// 设置跨域请求
		response.addHeader("Access-Control-Allow-Origin", requset.getHeader("Origin"));
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.addHeader("Access-Control-Allow-Headers", "x-requested-with,content-type");
		chain.doFilter(req, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
