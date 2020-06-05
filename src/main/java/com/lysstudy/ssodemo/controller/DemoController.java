package com.lysstudy.ssodemo.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class DemoController {
	@GetMapping("/auth/login")
	public ModelAndView formlogin(ModelAndView mv){
		mv.setViewName("/login.html");
		return mv;
	}
	@GetMapping("/test")
	@ResponseBody
	public String test(){
		return "success";
	}
	@GetMapping("/authytest")
	public String autest(){
		return "success";
	}
	@RequestMapping("/oauth/confirm_access")//路径必须是这个
    public String toAuthPage(){
        return "oauth";
    }
}
