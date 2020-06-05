package com.lysstudy.ssodemo.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.BadClientCredentialsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lysstudy.ssodemo.entity.OauthClientDetails;
import com.lysstudy.ssodemo.repository.ClientDetailsRepository;
@Service("testClientDetailsService")
public class ClientDetailsServiceimpl implements ClientDetailsService {
	private final ClientDetailsRepository clientDetailsRepository;
	private final ObjectMapper objectMapper;
	public ClientDetailsServiceimpl(ClientDetailsRepository clientDetailsRepository,ObjectMapper objectMapper) {
		this.clientDetailsRepository = clientDetailsRepository;
		this.objectMapper = objectMapper;
	}
	
	@Override
	public ClientDetails loadClientByClientId(String arg0)
			throws ClientRegistrationException {
		Optional<OauthClientDetails> findById = null ;
		try {
			findById = clientDetailsRepository.findById(arg0);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		return findById
				.map(clientDetails -> {
					BaseClientDetails details = new BaseClientDetails();
					details.setClientId(clientDetails.getClientId());
					details.setClientSecret(clientDetails.getClientSecret());
					details.setAuthorizedGrantTypes(Arrays.asList(clientDetails.getAuthorizedGrantTypes().split(",")));
					details.setResourceIds(Arrays.asList(Optional.ofNullable(clientDetails.getResourceIds()).orElse("").split(",")));
					details.setAuthorities(Arrays.stream(Optional.ofNullable(clientDetails.getAuthorities()).orElse("").split(","))
							.map(str -> new SimpleGrantedAuthority(String.format("ROLE_%s", str))).collect(Collectors.toList()));
					details.setScope(Arrays.asList(Optional.ofNullable(clientDetails.getScope()).orElse("").split(",")));
					details.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValidity());
					details.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValidity());
					details.setRegisteredRedirectUri(new HashSet(Arrays.asList(Optional.ofNullable(clientDetails.getWebServerRedirectUri()).orElse("").split(","))));
					if("true".equalsIgnoreCase(clientDetails.getAutoapprove()))
						details.setAutoApproveScopes(Arrays.asList(Optional.ofNullable(clientDetails.getScope()).orElse("").split(",")));
					
					try {
						if(clientDetails.getAdditionalInformation()!=null){
							details.setAdditionalInformation(objectMapper.readValue(clientDetails.getAdditionalInformation(), HashMap.class));
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return details;
				}).orElseThrow(BadClientCredentialsException::new);
	}

}
