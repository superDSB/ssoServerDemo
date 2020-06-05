package com.lysstudy.ssodemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lysstudy.ssodemo.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	public Optional<UserInfo> findUserInfoByTelno(Long telno);
}
