package com.mycom.myhouse.map.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.map.dao.MapDao;
import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.GugunDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.map.dto.MapParamDto;
import com.mycom.myhouse.map.dto.SearchResultDto;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

@Service
public class MapServiceImpl implements MapService{
	
	@Autowired
	MapDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@Override
	public SaleResultDto mapHouseList(MapParamDto mapParamDto) {
		SaleResultDto saleResultDto = new SaleResultDto();;
		try {
			List<SaleDto> list = dao.mapHouseList(mapParamDto);	//limit, offset
			int count = dao.mapHouseListCount(mapParamDto);
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return saleResultDto;
	}
	@Override
	public List<SearchResultDto> houseSearchByName(String searchWord) {
		List<SearchResultDto> list = null;
		try {
			list = dao.houseSearchByName(searchWord);	//limit, offset
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public SaleResultDto mapHouseListNo(int no) {
SaleResultDto saleResultDto = new SaleResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<SaleDto> list = dao.mapHouseListNo(no);
			int count = dao.mapHouseListNoCount(no);
			
			saleResultDto.setList(list);
			saleResultDto.setCount(count);
			
			saleResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			saleResultDto.setResult(FAIL);
		}
		
		return saleResultDto;
	}
	


}
