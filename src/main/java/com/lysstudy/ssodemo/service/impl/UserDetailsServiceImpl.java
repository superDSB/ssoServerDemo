package com.lysstudy.ssodemo.service.impl;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lysstudy.ssodemo.repository.UserInfoRepository;
@Service("testUserDetailService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private final UserInfoRepository userInfoRepository;
	@Autowired
	public UserDetailsServiceImpl(UserInfoRepository userInfoRepository) {
		this.userInfoRepository = userInfoRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		return userInfoRepository.findUserInfoByTelno(Long.parseLong(arg0))
				.map(userInfo -> {
					User.UserBuilder builder = User.builder();
					builder = builder.username(String.valueOf(userInfo.getTelno()));
					builder = builder.password(userInfo.getPassword());
					builder = builder.accountLocked(false);
					builder = builder.authorities(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
					return builder.build();
				}).orElseThrow(() -> new UsernameNotFoundException(String.format("UserName not found.[%s]",arg0)));
	}

}
