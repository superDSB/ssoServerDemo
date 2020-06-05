package com.lysstudy.ssodemo.controller;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("authorizationRequest")
@Slf4j
public class OauthController {
	private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	@RequestMapping("/authentication/form")
	//@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (null != savedRequest) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
        //如果访问的是接口资源
        return "访问的服务需要身份认证，请引导用户到登录页";
	}
}
