package com.lysstudy.ssodemo.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lysstudy.ssodemo.entity.UserInfo;
import com.lysstudy.ssodemo.repository.UserInfoRepository;

@RestController
public class SSOTestController {
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping("/adduser")
	public String addUser(@RequestBody UserInfo info){
		String password = info.getPassword();
		info.setPassword(passwordEncoder.encode(password));
		userInfoRepository.save(info);
		return "success";
	}
	@GetMapping("/user")
	public Principal getUser(Principal principal){
		System.out.println(principal);
		return principal;
	}
}
