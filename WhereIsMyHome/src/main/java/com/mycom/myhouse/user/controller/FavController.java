package com.mycom.myhouse.user.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.user.dto.FavDto;
import com.mycom.myhouse.user.dto.FavResultDto;
import com.mycom.myhouse.user.service.FavService;


@RestController
public class FavController {
	
	@Autowired
	FavService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@PostMapping("/users/fav")
	public ResponseEntity<FavResultDto> register(@RequestBody FavDto favDto){
		FavResultDto favResultDto = new FavResultDto();
		
		favResultDto = service.insertFav(favDto);
		
		if(favResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/users/fav/{no}")
	public ResponseEntity<FavResultDto> removeFav(@PathVariable int no, FavDto favDto){
		System.out.println(favDto);
		FavResultDto favResultDto = new FavResultDto();
		
		favResultDto = service.removeFav(favDto);
		
		if(favResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/users/fav")
	public ResponseEntity<FavResultDto> getUserFav(@RequestBody FavDto favDto){
		FavResultDto favResultDto = new FavResultDto();
		
		favResultDto = service.getUserFav(favDto);
		
		if(favResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<FavResultDto>(favResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
