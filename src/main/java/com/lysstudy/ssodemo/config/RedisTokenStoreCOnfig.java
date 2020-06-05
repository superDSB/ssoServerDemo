package com.lysstudy.ssodemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class RedisTokenStoreCOnfig {
	@Autowired
	private RedisConnectionFactory connect;
	@Bean
	public RedisTokenStore getRedisTokenStore(){
		return new RedisTokenStore(connect);
	}
}
