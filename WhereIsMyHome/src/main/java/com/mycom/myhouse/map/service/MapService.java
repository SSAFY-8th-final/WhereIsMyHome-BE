package com.mycom.myhouse.map.service;

import java.util.List;

import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.GugunDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.map.dto.MapParamDto;
import com.mycom.myhouse.map.dto.SearchResultDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

public interface MapService {

	SaleResultDto mapHouseList(MapParamDto mapParamDto);
	List<SearchResultDto> houseSearchByName(String searchWord);
	
	SaleResultDto mapHouseListNo(int no);
}
