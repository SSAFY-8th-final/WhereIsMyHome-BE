package com.mycom.myhouse.admin.controller;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhouse.admin.service.AdminEventService;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dto.UserDto;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class AdminEventController {

	@Autowired
	AdminEventService service;
	
	private final String SUCCESS = "success";
	
	// limit, offset, searchWord
	@GetMapping(value="/admins/events")
	public ResponseEntity<EventResultDto> eventList(EventParamDto eventParamDto) {
		
		EventResultDto eventResultDto;
		
		// service 호출할 때, searchWord 유무에 따라 분리해서 처리
		if(eventParamDto.getSearchWord() == null || eventParamDto.getSearchWord().isEmpty()) {
			if( eventParamDto.getOption() == null || eventParamDto.getOption().isEmpty())
				eventResultDto = service.eventList(eventParamDto);				
			else
				eventResultDto = service.eventListOption(eventParamDto);
		} else {
			if( eventParamDto.getOption() == null || eventParamDto.getOption().isEmpty() )
				eventResultDto = service.eventListSearchWord(eventParamDto);
			else
				eventResultDto = service.eventListSearchWordOption(eventParamDto);
		}
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@GetMapping(value="/admins/events/{eventKey}")
	public ResponseEntity<EventResultDto> eventDetail(@PathVariable int eventKey, UserDto user) {
		// BoardParamDto 만들기
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);  // PathVariable로 넘어온 게시글 key
		
		// BoardResultDto 만들기
		EventResultDto eventResultDto = service.eventDetail(eventParamDto);
		
		if(eventResultDto.getDto().getRegisterId().equals(user.getUserEmail()))
			eventResultDto.getDto().setSameUser(true);
		else
			eventResultDto.getDto().setSameUser(false);
			
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping(value="/admins/events") 
	public ResponseEntity<EventResultDto> eventInsert(EventDto dto, UserDto user, MultipartHttpServletRequest request) {
		
		dto.setRegisterId(user.getUserEmail());
		
		EventResultDto eventResultDto = service.eventInsert(dto, request);
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value="/admins/events/{eventKey}") 
	private ResponseEntity<EventResultDto> eventUpdate(EventDto dto, MultipartHttpServletRequest request) {
		// BoardResultDto 만들기
		EventResultDto eventResultDto = service.eventUpdate(dto, request);
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/admins/events/{eventKey}")
	private ResponseEntity<EventResultDto> eventDelete(@PathVariable int eventKey) {

		// BoardResultDto 만들기
		EventResultDto eventResultDto = service.eventDelete(eventKey);
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
}
