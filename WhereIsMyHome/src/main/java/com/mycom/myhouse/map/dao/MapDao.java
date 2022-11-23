package com.mycom.myhouse.map.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.GugunDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.map.dto.MapParamDto;
import com.mycom.myhouse.map.dto.SearchResultDto;

@Mapper
public interface MapDao {

	List<GugunDto> mapGugunList(String sidoCode);
	List<DongDto> mapDongList(String gugunCode);
	List<HouseDto> mapHouseList(MapParamDto mapParamDto);
	List<SearchResultDto> houseSearchByName(String searchWord);
}
