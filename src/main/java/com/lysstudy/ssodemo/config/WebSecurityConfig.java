package com.lysstudy.ssodemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	@Qualifier("testUserDetailService")
	private UserDetailsService userDetailsService;
	
	@Bean("testAuthenticationManager")
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests().antMatchers("/oauth/**").permitAll()
		.antMatchers("/test","/auth/login","/authentication/form").permitAll()
		.antMatchers("/static/**").permitAll()
		.antMatchers(HttpMethod.OPTIONS, "/**").anonymous()
		.anyRequest().authenticated()
		.and().formLogin().loginPage("/auth/login").loginProcessingUrl("/oauth/authorize")
		.permitAll()
		.and().httpBasic()
		.and()
		.csrf().disable();
	}
	/**
	 * 配置spring security验证方式，用户名密码的验证是基于security的
	 * 所以要在这里设置，不然会报需要先通过spring security验证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
	@Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/static/**");
    }
}
