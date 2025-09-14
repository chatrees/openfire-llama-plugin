package org.ifsoft.llama.openfire;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class CORSFilter implements Filter {
    private static final Logger Log = LoggerFactory.getLogger(CORSFilter.class);
	
	public CORSFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)	throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		Log.debug("CORSFilter HTTP Request: " + request.getMethod());

		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Private-Network", "true");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Origin", "*");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Headers", "*");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Request-Headers", "*");
		((HttpServletResponse) servletResponse).addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
		
		HttpServletResponse resp = (HttpServletResponse) servletResponse;

		if (request.getMethod().equals("OPTIONS")) {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			return;
		}
		chain.doFilter(request, servletResponse);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}
}