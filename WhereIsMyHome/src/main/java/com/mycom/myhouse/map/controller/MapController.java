package com.mycom.myhouse.map.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.map.dto.*;
import com.mycom.myhouse.map.service.MapService;

@RestController
public class MapController {
	
	@Autowired
	MapService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@GetMapping("/maps/houses") 
	private ResponseEntity<List<HouseDto>> mapHouseList(MapParamDto mapParamDto){
		List<HouseDto> list = service.mapHouseList(mapParamDto);
		
		if(list == null) return new ResponseEntity<List<HouseDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<HouseDto>>(list, HttpStatus.OK);
	}

	@GetMapping("/maps/gugun/{sidoCode}") 
	private ResponseEntity<List<GugunDto>> mapGugunList(@PathVariable String sidoCode){
		List<GugunDto> list = service.mapGugunList(sidoCode);
		
		if(list == null) return new ResponseEntity<List<GugunDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<GugunDto>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/maps/dong/{gugunCode}") 
	private ResponseEntity<List<DongDto>> mapDongList(@PathVariable String gugunCode){
		List<DongDto> list = service.mapDongList(gugunCode);
		
		if(list == null) return new ResponseEntity<List<DongDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<DongDto>>(list, HttpStatus.OK);
	}
}
