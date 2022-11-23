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
	@GetMapping("/maps/search/{searchWord}") 
	private ResponseEntity<List<SearchResultDto>> houseSearchByName(@PathVariable String searchWord){
		System.out.println("controller - houseSearchByName");
		List<SearchResultDto> list = service.houseSearchByName(searchWord);
		System.out.println(list);
		if(list == null) return new ResponseEntity<List<SearchResultDto>>(list, HttpStatus.NOT_FOUND);
		return new ResponseEntity<List<SearchResultDto>>(list, HttpStatus.OK);
	}

}
