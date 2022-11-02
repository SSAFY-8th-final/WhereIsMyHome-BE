package com.mycom.myhouse.event.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParamDto {

	// 이벤트 목록
	private int limit;
	private int offset;
	private String searchWord;
	
	// 이벤트 상세
	private int eventKey;
	private int userSeq;
	private String userEmail;
	
}
