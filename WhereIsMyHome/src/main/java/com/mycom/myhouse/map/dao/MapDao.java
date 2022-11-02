package com.mycom.myhouse.map.dao;

import java.util.List;

import com.mycom.myhouse.map.dto.DealDto;
import com.mycom.myhouse.map.dto.DongDto;
import com.mycom.myhouse.map.dto.LocDto;

public interface MapDao {

	List<DongDto> mapDongSelect(int gugunCode);

	

	List<DealDto> mapListSearch(int gugunCode, String dong, String searchWord);
	
	List<LocDto> mapLocList(int gugunCode, String dong, String searchWord);
}
