package com.lysstudy.ssodemo.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
@Configuration
@EnableAuthorizationServer
@Slf4j
public class Oauth2COnfig extends AuthorizationServerConfigurerAdapter {
	@Autowired
	@Qualifier("testUserDetailService")
	private UserDetailsService userDetailsService;
	@Autowired
	@Qualifier("testClientDetailsService")
	private ClientDetailsService clientDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("testAuthenticationManager")
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisTokenStore redisTokenStore;
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)
			throws Exception {
		endpoints.tokenStore(redisTokenStore)
				.tokenServices(getTokenService())
				.authenticationManager(authenticationManager)
				.userDetailsService(userDetailsService);
		if(log.isDebugEnabled()){
			log.debug("AuthorizationServerEndpointsConfigurer configuration finished");
		}
	}
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security)
			throws Exception {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()")
				.passwordEncoder(passwordEncoder);
		
		if(log.isDebugEnabled()){
			log.debug("AuthorizationServerSecurityConfigurer configuration finished");
		}
				
	}
	@Override
	public void configure(ClientDetailsServiceConfigurer clients)
			throws Exception {
		clients.withClientDetails(clientDetailsService);
		
		if(log.isDebugEnabled()){
			log.debug("ClientDetailsServiceConfigurer configuration finished");
		}
	}
	@Bean
	public DefaultTokenServices getTokenService(){
		DefaultTokenServices service = new DefaultTokenServices();
		service.setTokenStore(redisTokenStore);
		service.setClientDetailsService(clientDetailsService);
		service.setSupportRefreshToken(true);
		return service;
	}
}
