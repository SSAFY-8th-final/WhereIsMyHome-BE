package com.mycom.myhouse.sale.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;
import com.mycom.myhouse.sale.service.SaleService;

@RestController
public class SaleController {
	@Autowired
	SaleService service;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	// 테스트용
	@GetMapping("/sales")
	private ResponseEntity<SaleResultDto> saleList(SaleParamDto saleParamDto){
		
		SaleResultDto saleResultDto;
		
		if(saleParamDto.getSearchWord() == null || saleParamDto.getSearchWord().isEmpty()) {
			saleResultDto = service.saleList(saleParamDto);
		} else {
			saleResultDto = service.saleListSearchWord(saleParamDto);
		}
		
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/sales/{no}")
	private ResponseEntity<SaleResultDto> saleDetail(@PathVariable int no){
		SaleResultDto saleResultDto = service.saleDetail(no);
		
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/sales/dealer/{userEmail}") 
	private ResponseEntity<SaleResultDto> saleListDealer(@PathVariable String userEmail){
		SaleParamDto saleParamDto = new SaleParamDto();
		saleParamDto.setUserEmail(userEmail);
		
		SaleResultDto saleResultDto = service.saleListDealer(saleParamDto);
		
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/sales/dealer/{no}")
	private ResponseEntity<SaleResultDto> saleDelete(@PathVariable int no){
		SaleResultDto saleResultDto = service.saleDelete(no);
		
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/sales/dealer")
	private ResponseEntity<SaleResultDto> saleInsert(@RequestBody SaleDto saleDto){
		System.out.println("saleInsert " + saleDto);

		saleDto.setUserEmail("admin");
		
		SaleResultDto saleResultDto = service.saleInsert(saleDto);
		System.out.println(saleResultDto.getNo());
		if(saleDto.getMoveInCode().equals("002") &&saleResultDto.getResult().equals(SUCCESS)) {
			saleDto.setNo(saleResultDto.getNo());
			saleResultDto = service.saleUpdateMoveInDate(saleDto);			
		}
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/sales/dealer/{no}")
	private ResponseEntity<SaleResultDto> saleUpdate(SaleDto saleDto){
		
		SaleResultDto saleResultDto = service.saleUpdate(saleDto);
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/house")
	private ResponseEntity<SaleResultDto> houseInsert(@RequestBody HouseDto dto){
		SaleResultDto saleResultDto = service.houseInsert(dto);
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.OK);
		}else {
			return new ResponseEntity<SaleResultDto>(saleResultDto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/house/search-address")
	private ResponseEntity<Integer> houseSearch(@RequestBody HouseDto dto){
		SaleResultDto saleResultDto = service.houseSearchByAddress(dto);
		if(saleResultDto.getResult().equals(SUCCESS)) {
			return new ResponseEntity<Integer>(saleResultDto.getNo(), HttpStatus.OK);
		}else {
			return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
