package com.mycom.myhouse.sale.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.event.dao.EventDao;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.sale.dao.SaleDao;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;
import com.mycom.myhouse.user.dao.UserDao;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	SaleDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@Override
	public SaleResultDto saleDelete(int no) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.saleDelete(no);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleListDealer(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			List<SaleDto> list = dao.saleListDealer(saleParamDto);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleDetail(int no) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			SaleDto dto = dao.saleDetail(no);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setDto(dto);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto saleInsert(SaleDto saleDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
//			if(saleDto.getMoveInCode() != "001")
			
			String moveInDateStr = saleDto.getMoveInDateStr();
			if(moveInDateStr != null && moveInDateStr != "") {
				// 포맷터
		        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 
		        // 문자열 -> Date
		        Date date = formatter.parse(moveInDateStr);
				saleDto.setMoveInDate(date);
			}
			
			dao.saleInsert(saleDto);
			saleResultDto.setResult(SUCCESS);
			saleResultDto.setNo(saleDto.getNo());
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}
	@Override
	public SaleResultDto saleUpdate(SaleDto saleDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
//			if(saleDto.getMoveInCode() != "001")
			
			String moveInDateStr = saleDto.getMoveInDateStr();
			if(moveInDateStr != null && moveInDateStr != "") {
				// 포맷터
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				
				// 문자열 -> Date
				Date date = formatter.parse(moveInDateStr);
				saleDto.setMoveInDate(date);
			}
			
			dao.saleUpdate(saleDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

}
