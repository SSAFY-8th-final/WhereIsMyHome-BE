package com.mycom.myhouse.admin.dto;

public class CodeDto {
	
	private String childCode;
	private String childName;
	public String getChildCode() {
		return childCode;
	}
	public void setChildCode(String childCode) {
		this.childCode = childCode;
	}
	public String getChildName() {
		return childName;
	}
	public void setChildName(String childName) {
		this.childName = childName;
	}
	@Override
	public String toString() {
		return "CodeDto [childCode=" + childCode + ", childName=" + childName + "]";
	}

	
}
