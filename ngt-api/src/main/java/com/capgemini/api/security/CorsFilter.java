package com.capgemini.api.security;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//import org.springframework.stereotype.Component;
//
//@Component
//public class CorsFilter implements Filter {
//	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) 
//			throws IOException, ServletException{
//		HttpServletResponse response = (HttpServletResponse) res;
//		response.setHeader("Acess-Control-Allow-Origin", "*");
//		response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Max-Age",  "3600");
//		chain.doFilter(req, res);
//	}
//
//	
//}