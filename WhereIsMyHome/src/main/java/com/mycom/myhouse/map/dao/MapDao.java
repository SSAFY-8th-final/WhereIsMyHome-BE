package com.mycom.myhouse.map.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.GugunDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.map.dto.MapParamDto;
import com.mycom.myhouse.map.dto.SearchResultDto;
import com.mycom.myhouse.sale.dto.SaleDto;

@Mapper
public interface MapDao {

	List<SaleDto> mapHouseList(MapParamDto mapParamDto);
	int mapHouseListCount(MapParamDto mapParamDto);
	
	List<SearchResultDto> houseSearchByName(String searchWord);
	List<SaleDto> mapHouseListNo(int no);
	int mapHouseListNoCount(int no);
	
	int[] getUserFav(int userSeq); // 로그인한 사용자의 찜한 매물

}
