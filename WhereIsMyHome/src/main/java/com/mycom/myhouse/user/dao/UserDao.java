package com.mycom.myhouse.user.dao;

import com.mycom.myhouse.user.dto.UserDto;

public interface UserDao {
	
	int userUpdate(UserDto dto);
	int userDelete(String userEmail);
}
