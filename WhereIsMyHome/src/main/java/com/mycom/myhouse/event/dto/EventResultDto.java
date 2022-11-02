package com.mycom.myhouse.event.dto;

import java.util.Date;
import java.util.List;

import com.mycom.myhouse.user.dto.UserDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EventResultDto {
	private int result;	// 결과
	private UserDto UserDto;	// 이벤트 하나
	private List<EventDto> eventList;	// 이벤트 목록
}
