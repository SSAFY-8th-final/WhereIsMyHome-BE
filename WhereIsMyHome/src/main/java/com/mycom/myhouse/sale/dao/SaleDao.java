package com.mycom.myhouse.sale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;

@Mapper
public interface SaleDao {
	List<SaleDto> saleList(SaleParamDto saleParamDto);
	int saleListTotalCount(SaleParamDto saleParamDto);
	
	// search word
	List<SaleDto> saleListSearchWord(SaleParamDto saleParamDto);
	int saleListSearchWordTotalCount(SaleParamDto saleParamDto);
	
	int[] getUserFav(int userSeq); // 로그인한 사용자의 찜한 매물
	List<SaleDto> popularSale(String dongCode); // 인기매물
	List<SaleDto> popularSaleInfo(int no);
	
	void saleInsert(SaleDto saleDto);
	void saleUpdate(SaleDto saleDto);
	void saleDelete(int no);

	List<SaleDto> saleListDealer(SaleParamDto saleParamDto);

	SaleDto saleDetail(int no);
	String houseSearchByAddress(HouseDto dto);
	void houseInsert(HouseDto dto);
	void saleUpdateMoveInDate(SaleDto dto);
	int saleListDealerCount(SaleParamDto saleParamDto);
}