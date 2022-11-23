package com.mycom.myhouse.event.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.event.service.EventService;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;
import com.mycom.myhouse.user.service.UserService;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class EventController {
	
	@Autowired
	EventService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@GetMapping("/events") 
	private ResponseEntity<EventResultDto> eventList(EventParamDto eventDto){
		EventParamDto eventParamDto = new EventParamDto();
		
		EventResultDto eventResultDto = service.eventList(eventParamDto);
		
		if(eventResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/events/{eventKey}")
	private ResponseEntity<EventResultDto> eventDetail(@PathVariable int eventKey, UserDto user){
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);
		eventParamDto.setUserSeq(user.getUserSeq());
		
		EventResultDto eventResultDto = service.eventDetail(eventParamDto);
		
		if(eventResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/events/{eventKey}")
	private ResponseEntity<Map<String, String>> eventAttend(@PathVariable int eventKey, UserDto user){
		Map<String, String> map = new HashMap<>();
		
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);
		eventParamDto.setUserEmail(user.getUserEmail());
		
		
		EventResultDto eventResultDto = service.eventAttend(eventParamDto);
		if(eventResultDto.getResult().equals(SUCCESS)) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/events/{eventKey}")
	private ResponseEntity<Map<String, String>> leaveEvent(@PathVariable int eventKey, UserDto user){
		Map<String, String> map = new HashMap<>();
		
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);
		eventParamDto.setUserEmail(user.getUserEmail());
		
		
		EventResultDto eventResultDto = service.leaveEvent(eventParamDto);
		if(eventResultDto.getResult().equals(SUCCESS)) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
