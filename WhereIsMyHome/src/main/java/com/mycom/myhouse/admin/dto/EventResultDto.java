package com.mycom.myhouse.admin.dto;

import java.util.List;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResultDto {

	private int result;
	private EventDto dto;  // 게시글 상세
	private List<EventDto> list;  // 게시글 목록
	private int count;  // 게시글 총 건수
	
}
