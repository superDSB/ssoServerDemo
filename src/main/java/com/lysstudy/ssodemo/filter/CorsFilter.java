package com.lysstudy.ssodemo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
@Component
public class CorsFilter extends OncePerRequestFilter{
	private static final String REQUEST_OPTIONS = "OPTIONS";
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String origin  = request.getHeader(HttpHeaders.ORIGIN);

        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PATCH, PUT, HEAD");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
        response.addHeader("Access-Control-Max-Age", "36000");
        if (REQUEST_OPTIONS.equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpStatus.OK.value());
            return;
        }
        filterChain.doFilter(request, response);

	}
	
}
