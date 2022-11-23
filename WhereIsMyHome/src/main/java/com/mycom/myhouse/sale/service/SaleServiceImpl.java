package com.mycom.myhouse.sale.service;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.sale.dao.SaleDao;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

@Service
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	SaleDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";

	@Override
	public SaleResultDto saleList(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<SaleDto> list = dao.saleList(saleParamDto);
			int count = dao.saleListTotalCount(saleParamDto);
			
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		
		return saleResultDto;
	}
	
	@Override
	public SaleResultDto saleListSearchWord(SaleParamDto saleParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<SaleDto> list = dao.saleListSearchWord(saleParamDto);
			int count = dao.saleListSearchWordTotalCount(saleParamDto);
			
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		
		return saleResultDto;
	}
	
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
	public SaleResultDto saleUpdateMoveInDate(Date date) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			SaleDto dto = new SaleDto();
						
			dto.setMoveInDate(date);
			dao.saleUpdateMoveInDate(dto);
			saleResultDto.setResult(SUCCESS);
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
			
//			String moveInDateStr = saleDto.getMoveInDateStr();
//			if(moveInDateStr != null && moveInDateStr != "") {
//				// 포맷터
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//				
//				// 문자열 -> Date
//				Date date = formatter.parse(moveInDateStr);
//				saleDto.setMoveInDate(date);
//			}
			
			dao.saleUpdate(saleDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto houseSearchByAddress(HouseDto dto) {
		System.out.println("saleServiceImpl - houseSearchByAddr");
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			int no = -1;
			String noStr = dao.houseSearchByAddress(dto);
			if(noStr != null) no = Integer.parseInt(noStr);
			
			saleResultDto.setNo(no);
			saleResultDto.setResult(SUCCESS);
			System.out.println(no);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}

	@Override
	public SaleResultDto houseInsert(HouseDto dto) {
		SaleResultDto saleResultDto = new SaleResultDto();
		try {
			dao.houseInsert(dto);
			saleResultDto.setNo(dto.getNo());
			System.out.println("saleServiceImpl - houseInsert " + saleResultDto);
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		return saleResultDto;
	}
}
