package com.mycom.myhouse.map.service;

import java.util.List;

import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.GugunDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.map.dto.MapParamDto;
import com.mycom.myhouse.map.dto.SearchResultDto;

public interface MapService {

	List<GugunDto> mapGugunList(String sidoCode);
	List<DongDto> mapDongList(String gugunCode);
	List<HouseDto> mapHouseList(MapParamDto mapParamDto);
	List<SearchResultDto> houseSearchByName(String searchWord);
}
