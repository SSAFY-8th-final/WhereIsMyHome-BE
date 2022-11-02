package com.mycom.myhouse.user.dao;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.user.dto.UserDto;

@Mapper
public interface LoginDao {
	UserDto login(String userEmail);
}
