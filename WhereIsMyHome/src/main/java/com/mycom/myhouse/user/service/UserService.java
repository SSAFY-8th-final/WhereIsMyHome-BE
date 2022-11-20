package com.mycom.myhouse.user.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;



public interface UserService {
	//List<UserDto> list();
	UserResultDto userDetail(String userEmail);
	UserResultDto register(UserDto dto);
	UserResultDto userUpdate(UserDto dto);
	UserResultDto userDelete(UserDto dto);
	UserResultDto login(UserDto dto);
	EventResultDto userEventAttendList(String userEmail);
	
	void saveRefreshToken(UserDto dto);
	UserResultDto getRefreshToken(String userEmail);
	UserResultDto deleteRefreshToken(String userEmail);
}
