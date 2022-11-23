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

@Service
public class MapServiceImpl implements MapService{
	
	@Autowired
	MapDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@Override
	public List<GugunDto> mapGugunList(String sidoCode) {
		List<GugunDto> list = null;
		try {
			list = dao.mapGugunList(sidoCode);	//limit, offset
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public List<DongDto> mapDongList(String gugunCode) {
		List<DongDto> list = null;
		try {
			list = dao.mapDongList(gugunCode);	//limit, offset
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	@Override
	public List<HouseDto> mapHouseList(MapParamDto mapParamDto) {
		List<HouseDto> list = null;
		try {
			list = dao.mapHouseList(mapParamDto);	//limit, offset
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
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
	


}
