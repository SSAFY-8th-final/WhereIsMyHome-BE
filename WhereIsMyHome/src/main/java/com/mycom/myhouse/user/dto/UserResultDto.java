package com.mycom.myhouse.user.dto;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserResultDto {
	private String result;	// 결과
	private UserDto UserDto;	// 유저 하나
	private List<UserDto> list;	// 유저 목록
	
}
