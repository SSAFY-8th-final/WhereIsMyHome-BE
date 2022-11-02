package com.mycom.myhouse.admin.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.admin.dto.EventParamDto;
import com.mycom.myhouse.admin.dto.EventResultDto;
import com.mycom.myhouse.admin.service.EventService;

@RestController
public class AdminEventController {

	@Autowired
	EventService service;
	
	@GetMapping(value="/admins/events")
	public ResponseEntity<EventResultDto> eventList(EventParamDto eventDto) {
		
	}
	
	@GetMapping(value="/admins/events/{eventKey}")
	public ResponseEntity<EventResultDto> eventDetail(@PathVariable int eventKey) {
		
	}
	
	@PostMapping(value="/admins/events") 
	public ResponseEntity<EventResultDto> eventInsert(EventParamDto eventDto, HttpSession session) {
		
	}
	
	@PutMapping(value="/admins/events/{eventKey}") 
	private ResponseEntity<EventResultDto> eventUpdate(EventParamDto eventDto, HttpSession session) {
		
	}
	
	@DeleteMapping("/admins/events/{eventKey}")
	private ResponseEntity<EventResultDto> eventDelete(@PathVariable int eventKey) {
		
	}
	
}
