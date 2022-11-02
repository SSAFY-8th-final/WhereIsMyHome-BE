package com.mycom.myhouse.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.user.dto.UserDto;

@Mapper
public interface UserDao {
	int register(UserDto dto);
	UserDto userDetail(String userEmail);
	int userUpdate(UserDto dto);
	int userDelete(String userEmail);
	UserDto login(String userEmail);
	List<EventDto> userEventAttendList(String userEmail);
}
