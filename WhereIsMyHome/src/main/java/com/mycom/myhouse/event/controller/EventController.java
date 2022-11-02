package com.mycom.myhouse.event.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.event.service.EventService;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;
import com.mycom.myhouse.user.service.UserService;

@RestController
public class EventController {
	
	@Autowired
	EventService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@GetMapping("/events") 
	private ResponseEntity<EventResultDto> eventList(EventParamDto eventDto){
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setOffset(0);
		eventParamDto.setLimit(10);
		
		
		EventResultDto eventResultDto = service.eventList(eventParamDto);
		
		if(eventResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/events/{eventKey}")
	private ResponseEntity<EventResultDto> eventDetail(@PathVariable int eventKey){
		EventResultDto eventResultDto = service.eventDetail(eventKey);
		
		if(eventResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping("/events/{eventKey}")
	private ResponseEntity<Map<String, String>> eventAttend(@PathVariable int eventKey, HttpSession session){
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		Map<String, String> map = new HashMap<>();
		
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);
		eventParamDto.setUserEmail(userDto.getUserEmail());
		
		
		EventResultDto eventResultDto = service.eventAttend(eventParamDto);
		if(eventResultDto.getResult().equals(SUCCESS)) {
			map.put("result", "success");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}else {
			map.put("result", "fail");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
