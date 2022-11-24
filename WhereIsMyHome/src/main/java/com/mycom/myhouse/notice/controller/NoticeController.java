package com.mycom.myhouse.notice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;
import com.mycom.myhouse.notice.service.NoticeService;
import com.mycom.myhouse.user.dto.UserDto;

@RestController
@CrossOrigin(
		// localhost:5500 과 127.0.0.1 구분
		origins = "http://localhost:5500", // allowCredentials = "true" 일 경우, orogins="*" 는 X
		allowCredentials = "true", 
		allowedHeaders = "*", 
		methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT,RequestMethod.HEAD,RequestMethod.OPTIONS}
	)
public class NoticeController {
	
	@Autowired
	NoticeService service;
	
	private final String SUCCESS = "success";
	
	// limit, offset, searchWord
	@GetMapping(value="/notices")
	public ResponseEntity<NoticeResultDto> noticeList(NoticeParamDto noticeParamDto) {
		System.out.println("noticeList--" + noticeParamDto);
		
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
	
	@GetMapping(value="/notices/{noticeId}")
	public ResponseEntity<NoticeResultDto> noticeDetail(@PathVariable int noticeId, UserDto user) {
		// BoardParamDto 만들기
		NoticeParamDto noticeParamDto = new NoticeParamDto();
		noticeParamDto.setNoticeId(noticeId);  // PathVariable로 넘어온 게시글 key
		System.out.println(user);
		noticeParamDto.setUserSeq(user.getUserSeq());
		
		// BoardResultDto 만들기
		NoticeResultDto noticeResultDto = service.noticeDetail(noticeParamDto);
		
		if (noticeResultDto.getResult() == SUCCESS) {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<NoticeResultDto>(noticeResultDto, HttpStatus.INTERNAL_SERVER_ERROR);			
		}
	}
	
}
