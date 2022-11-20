package com.mycom.myhouse.notice.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class NoticeDto {
	private int noticeId;
	private String title;
	private String content;
	private LocalDateTime regDt;
	private String userEmail;
	private int readCount;
	private boolean sameUser;
}
