package com.mycom.myhouse.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.admin.dto.CodeDto;
import com.mycom.myhouse.admin.service.CodeService;

@RestController
public class CodeController {

	@Autowired
	CodeService service;
	
	@GetMapping(value="/admins/grades")
	private ResponseEntity<List<CodeDto>> codeList(@RequestParam String parentCode) {
		System.out.println(parentCode);
		
		List<CodeDto> list = service.codeList(parentCode);
		
		if(list != null) {
			return new ResponseEntity<List<CodeDto>>(list, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<CodeDto>>(list, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
