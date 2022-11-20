package com.mycom.myhouse.event.dto;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventDto {

	private int eventKey;
	private String name;
	private String content;
	private String category;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private String statusCode;
	private String registerId;
	private LocalDateTime registerDateTime;
	private int readCount;
	private int attendCount;
	private boolean sameUser; // 글쓴이와 보는 이가 같은 사용자인지 여부
	private boolean isAttend;
	private List<EventFileDto> fileList;
	
//	public void setStartDateTime(Date startDateTime) {
//		// mybatis에서 date mapping (LocalDateTime을 지원하지 않음)
//		this.startDateTime = LocalDateTime.ofInstant(startDateTime.toInstant(), ZoneId.systemDefault());
//	}
//	public void setEndDateTime(Date endDateTime) {
//		// mybatis에서 date mapping (LocalDateTime을 지원하지 않음)
//		this.endDateTime = LocalDateTime.ofInstant(endDateTime.toInstant(), ZoneId.systemDefault());
//	}
//	public void setRegisterDateTime(Date registerDateTime) {
//		// mybatis에서 date mapping (LocalDateTime을 지원하지 않음)
//		this.registerDateTime = LocalDateTime.ofInstant(registerDateTime.toInstant(), ZoneId.systemDefault());
//	}
}
