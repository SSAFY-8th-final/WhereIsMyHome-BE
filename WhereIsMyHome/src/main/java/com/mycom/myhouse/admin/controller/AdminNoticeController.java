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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.admin.service.AdminNoticeService;
import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;
import com.mycom.myhouse.user.dto.UserDto;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class AdminNoticeController {

	@Autowired
	AdminNoticeService service;
	
	private final String SUCCESS = "success";
	
	// limit, offset, searchWord
	@GetMapping(value="/admins/notices")
	public ResponseEntity<NoticeResultDto> noticeList(NoticeParamDto noticeParamDto) {
		
		NoticeResultDto noticeResultDto;
		
		// service 호출할 때, searchWord 유무에 따라 분리해서 처리
		if(noticeParamDto.getSearchWord() == null || noticeParamDto.getSearchWord().isEmpty()) {
			noticeResultDto = service.noticeList(noticeParamDto);
		} else {
			noticeResultDto = service.noticeListSearchWord(noticeParamDto);
		}
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@GetMapping(value="/admins/notices/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeDetail(@PathVariable int noticeId, UserDto user, HttpSession session) {
		// BoardParamDto 만들기
		NoticeParamDto noticeParamDto = new NoticeParamDto();
		noticeParamDto.setNoticeId(noticeId);  // PathVariable로 넘어온 게시글 key
		
		System.out.println(user);
		
		// BoardResultDto 만들기
		NoticeResultDto noticeResultDto = service.noticeDetail(noticeParamDto);
		
		if(noticeResultDto.getDto().getUserEmail().equals(user.getUserEmail()))
			noticeResultDto.getDto().setSameUser(true);
		else
			noticeResultDto.getDto().setSameUser(false);
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PostMapping(value="/admins/notices") 
	public ResponseEntity<NoticeResultDto> noticeInsert(@RequestBody NoticeDto dto) {
		
		NoticeResultDto noticeResultDto = service.noticeInsert(dto);
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@PutMapping(value="/admins/notices/{noticeId}") 
	private ResponseEntity<NoticeResultDto> noticeUpdate(@RequestBody NoticeDto dto) {
		// BoardResultDto 만들기
		NoticeResultDto noticeResultDto = service.noticeUpdate(dto);
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
	@DeleteMapping("/admins/notices/{noticeId}")
	private ResponseEntity<NoticeResultDto> noticeDelete(@PathVariable int noticeId) {

		// BoardResultDto 만들기
		NoticeResultDto noticeResultDto = service.noticeDelete(noticeId);
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
}
