package com.mycom.myhouse.notice.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoticeParamDto {
	// 공지사항 목록
	private int limit;
	private int offset;
	private String searchWord;
	
	// 공지 상세
	private int noticeId;
	private int userSeq;
	private String userEmail;
}
