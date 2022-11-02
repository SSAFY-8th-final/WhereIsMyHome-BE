package com.mycom.myhouse.admin.dto;

import java.util.Date;

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
	private Date startDateTime;
	private Date endDateTime;
	private String url;
	private String statusCode;
	private String registerId;
	private Date registerDateTime;
	private boolean sameUser; // 글쓴이와 보는 이가 같은 사용자인지 여부
  
}
