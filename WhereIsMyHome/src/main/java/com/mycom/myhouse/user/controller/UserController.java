package com.mycom.myhouse.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;
import com.mycom.myhouse.user.service.UserService;


@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	private final int SUCCESS = 1;
	
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(UserDto dto, HttpSession session){
        // dto 에는 client 가 보낸 userEmail, userPassword 가 자동으로 파라미터 처리되어진다.
    	UserResultDto userResultDto = service.login(dto);
    	UserDto userDto = userResultDto.getUserDto();
    	
        Map<String, String> map = new HashMap<>();
        if( userDto != null ) { // login 성공
            // session 에 userDto 를 저장
            session.setAttribute("userDto", userDto);
            
            System.out.println("login 성공");

            // client 에게 성공 결과를 json 으로 전달
            map.put("result", "success");

            // html 로 client 를 구성하므로 html 에서 server session 에 접근 X
            // 로그인 성공 직후에 client 에게 client 가 필요로 하는 사용자 정보를 내려줘야 한다.
            map.put("userName", userDto.getUserName());
            map.put("userProfileImageUrl", userDto.getUserProfileImageUrl());

            return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
        }

        map.put("result", "fail");
        return new ResponseEntity<Map<String, String>>(map, HttpStatus.NOT_FOUND);
    }
	
	// HttpStatus Code로 처리결과를 Wrapping 하기 위해 ResponseEntity를 사용
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> register(UserDto userDto){
		System.out.println(userDto);
		UserResultDto userResultDto = service.register(userDto);
		
		Map<String, String> map = new HashMap<>();
		if(userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(value="/users/{userEmail}")
	public ResponseEntity<UserResultDto> userDetail(@PathVariable String userEmail){
		System.out.println("detail : userEmail : " + userEmail);
		UserResultDto userResultDto = service.userDetail(userEmail);
		
		if(userResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(userResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/users")
	public ResponseEntity<UserResultDto> userUpdate(UserDto userDto, HttpSession session){
		UserResultDto UserResultDto = service.userUpdate(userDto);
		
		if(UserResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<UserResultDto>(UserResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<UserResultDto>(UserResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/users")
	public ResponseEntity<Map<String, String>> userDelete(HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		Map<String, String> map = new HashMap<>();
		
		if(userDto==null) {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		UserResultDto userResultDto = service.userDelete(userDto);
		
		if(userResultDto.getResult() == SUCCESS) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@GetMapping("/users/eventList")
	public ResponseEntity<EventResultDto> userEventAttendList(HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		
		EventResultDto eventResultDto = service.userEventAttendList(userDto.getUserEmail());
		
		if(eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
