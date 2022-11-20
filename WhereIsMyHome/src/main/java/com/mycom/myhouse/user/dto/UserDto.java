package com.mycom.myhouse.user.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDto {
		private int userSeq;
		private String userName;
	    private String userPassword;
	    private String userEmail;
	    private String userProfileImageUrl;
	    private Date userRegisterDate;
	    private String interestCode;
	    private String gradeCode;
	    
	    private String token;
		
	    public void setUserProfileImageUrl(String userProfileImageUrl) {
	        if( userProfileImageUrl == null || "null".equals(userProfileImageUrl) || "".equals(userProfileImageUrl)) {
	            this.userProfileImageUrl = "/img/noProfile.png";
	        }else {
	            this.userProfileImageUrl = userProfileImageUrl;
	        }
	    }
}
