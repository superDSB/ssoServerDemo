package com.lysstudy.ssodemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysstudy.ssodemo.entity.OauthClientDetails;

public interface ClientDetailsRepository extends JpaRepository<OauthClientDetails, String> {
	
}
