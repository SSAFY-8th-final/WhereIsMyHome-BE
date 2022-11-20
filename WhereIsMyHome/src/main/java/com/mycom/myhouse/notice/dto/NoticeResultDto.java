package com.mycom.myhouse.notice.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeResultDto {
	
	private String result;
	private NoticeDto dto;  // 게시글 상세
	private List<NoticeDto> list;  // 게시글 목록
	private int count;  // 게시글 총 건수
}
