package com.mycom.myhouse.event.dto;

import java.util.Date;

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
  
	public int getEventKey() {
		return eventKey;
	}
	public void setEventKey(int eventKey) {
		this.eventKey = eventKey;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public Date getRegisterDateTime() {
		return registerDateTime;
	}
	public void setRegisterDateTime(Date registerDateTime) {
		this.registerDateTime = registerDateTime;
	}
	public boolean isSameUser() {
		return sameUser;
	}
	public void setSameUser(boolean sameUser) {
		this.sameUser = sameUser;
	}
	
		
		  
  
	  
	  
	  
	
}
