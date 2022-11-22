package com.mycom.myhouse.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;
import com.mycom.myhouse.user.service.JwtService;
import com.mycom.myhouse.user.service.UserService;


@RestController
public class UserController {
	
	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	UserService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
    @PostMapping("/login")
    public ResponseEntity<UserResultDto> login(@RequestBody UserDto dto){

    	UserResultDto userResultDto = null;
		HttpStatus status = null;
		try {
			System.out.println(dto);
	    	userResultDto = service.login(dto);
	    	UserDto loginUser = userResultDto.getUserDto();
	    	
	    	logger.info("login 시도 - " + loginUser);
	    	
			if (loginUser != null) {
				//Object test = jwtService.createAccessToken("userEmail", loginUser.getUserEmail());// key, data
				//System.out.println(test);
				//String accessToken = (String) test;
				String accessToken = jwtService.createAccessToken("userEmail", loginUser.getUserEmail());// key, data
				String refreshToken = jwtService.createRefreshToken("userEmail", loginUser.getUserEmail());// key, data
				
				dto.setToken(refreshToken);
				dto.setUserPassword(null);
				service.saveRefreshToken(dto);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				userResultDto.setAccessToken(accessToken);
				userResultDto.setRefreshToken(refreshToken);
//				resultMap.put("access-token", accessToken);
//				resultMap.put("refresh-token", refreshToken);
//				resultMap.put("message", SUCCESS);
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.UNAUTHORIZED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<UserResultDto>(userResultDto, status);
    }
    
    @GetMapping("/logout/{userEmail}")
	public ResponseEntity<UserResultDto> logout(@PathVariable String userEmail) {
		UserResultDto userResultDto = service.deleteRefreshToken(userEmail);
		if(userResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<>(userResultDto, HttpStatus.OK);
		}
		return new ResponseEntity<>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// HttpStatus Code로 처리결과를 Wrapping 하기 위해 ResponseEntity를 사용
	@PostMapping("/register")
	public ResponseEntity<UserResultDto> register(@RequestBody UserDto userDto){
		System.out.println(userDto);
		UserResultDto userResultDto = service.register(userDto);
		
		Map<String, String> map = new HashMap<>();
		if(userResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/users/{userEmail}")
	public ResponseEntity<UserResultDto> userDetail(@PathVariable String userEmail, HttpServletRequest request){
		logger.info("detail : userEmail : " + userEmail);
		
		UserResultDto userResultDto = null;
		HttpStatus status = null;
		if (jwtService.checkToken(request.getHeader("Authrozation"))) {
			userResultDto = service.userDetail(userEmail);
			
			if(userResultDto.getResult().equals(SUCCESS)) 
				status = HttpStatus.OK;
			else 
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			
		} else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<UserResultDto>(userResultDto, status);
		
	}
	@PatchMapping("/users/{userEmail}")
	public ResponseEntity<UserResultDto> userUpdate(@PathVariable String userEmail,@RequestBody UserDto userDto, HttpServletRequest request){
		logger.info("userUpdate: " + userDto);
		UserResultDto userResultDto = null;
		HttpStatus status = null;
		if (jwtService.checkToken(request.getHeader("Authrozation"))) {
			System.out.println("토큰 통과");
			userDto.setUserEmail(userEmail);
			userResultDto = service.userUpdate(userDto);
			System.out.println(userResultDto.getResult());
			if(userResultDto.getResult().equals(SUCCESS)) {
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<UserResultDto>(userResultDto, status);
	}
	
	
	@PostMapping("/users/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody UserDto dto, HttpServletRequest request)
			throws Exception {
		UserResultDto userResultDto = null;
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		logger.debug("token : {}, userDto : {}", token, dto);
		if (jwtService.checkToken(token)) {
			userResultDto = service.getRefreshToken(dto.getUserEmail());
			if (token.equals(userResultDto.getRefreshToken())) {
				String accessToken = jwtService.createAccessToken("userEmail", dto.getUserEmail());// key, data
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				userResultDto.setAccessToken(accessToken);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<UserResultDto>(userResultDto, status);
	}
	 @PostMapping("/checkPw")
	    public ResponseEntity<UserResultDto> checkPw(@RequestBody UserDto dto){

	    	UserResultDto userResultDto = null;
			HttpStatus status = null;
			try {
				System.out.println(dto);
		    	userResultDto = service.login(dto);
		    	UserDto loginUser = userResultDto.getUserDto();
		    	
				if (loginUser != null) {		
					status = HttpStatus.OK;
				} else {
					status = HttpStatus.UNAUTHORIZED;
				}
			} catch (Exception e) {
				logger.error("비밀번호 확인 실패 : {}", e);
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
			return new ResponseEntity<UserResultDto>(userResultDto, status);
	    }
	 @DeleteMapping("/user/{userEmail}")
	 public ResponseEntity<UserResultDto> userDelete(@PathVariable String userEmail, HttpServletRequest request){
		 
		 UserResultDto userResultDto = null;
		 HttpStatus status = null;
		 
		 if (jwtService.checkToken(request.getHeader("Authrozation"))) {
			userResultDto = service.userDelete(userEmail);
			
			if(userResultDto.getResult().equals(SUCCESS)) {
				status = HttpStatus.OK;
			} else {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		}else {
			logger.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		 return new ResponseEntity<UserResultDto>(userResultDto, status);
	 }
	
	@GetMapping("/users/eventList")
	public ResponseEntity<EventResultDto> userEventAttendList(HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		EventResultDto eventResultDto = service.userEventAttendList(userDto.getUserEmail());
		
		if(eventResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
