package com.mycom.myhouse.admin.controller;

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

import com.mycom.myhouse.admin.dto.EventDto;
import com.mycom.myhouse.admin.dto.EventParamDto;
import com.mycom.myhouse.admin.dto.EventResultDto;
import com.mycom.myhouse.admin.service.EventService;
import com.mycom.myhouse.user.dto.UserDto;

@RestController
public class AdminEventController {

	@Autowired
	EventService service;
	
	private final int SUCCESS = 1;
	
	// limit, offset, searchWord
	@GetMapping(value="/admins/events")
	public ResponseEntity<EventResultDto> eventList(EventParamDto eventParamDto) {
		EventResultDto eventResultDto;
		
		// service 호출할 때, searchWord 유무에 따라 분리해서 처리
		if(eventParamDto.getSearchWord() == null || eventParamDto.getSearchWord().isEmpty()) {
			eventResultDto = service.eventList(eventParamDto);
		} else {
			eventResultDto = service.boardListSearchWord(eventParamDto);
		}
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@GetMapping(value="/admins/events/{eventKey}")
	public ResponseEntity<EventResultDto> eventDetail(@PathVariable int eventKey, HttpSession session) {
		// BoardParamDto 만들기
		EventParamDto eventParamDto = new EventParamDto();
		eventParamDto.setEventKey(eventKey);  // PathVariable로 넘어온 게시글 key
		UserDto userDto = (UserDto) session.getAttribute("userDto"); // 현재 로그인 + 상세페이지 요청한 사용자
		eventParamDto.setUserSeq(userDto.getUserSeq());
		
		// BoardResultDto 만들기
		EventResultDto eventResultDto = service.eventDetail(eventParamDto);
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping(value="/admins/events") 
	public ResponseEntity<EventResultDto> eventInsert(EventDto dto, HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		dto.setRegisterId(userDto.getUserName());
		
		System.out.println(dto);
		
		EventResultDto eventResultDto = service.eventInsert(dto);
		
		if (eventResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<EventResultDto>(eventResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PutMapping(value="/admins/events/{eventKey}") 
	private ResponseEntity<EventResultDto> eventUpdate(EventDto dto, HttpSession session) {
		// BoardResultDto 만들기
		EventResultDto eventResultDto = service.eventUpdate(dto);
		
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
