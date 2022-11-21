package com.mycom.myhouse.sale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

@Mapper
public interface SaleDao {
	List<SaleDto> saleList(SaleParamDto saleParamDto);
	int saleListTotalCount(SaleParamDto saleParamDto);
	
	// search word
	List<SaleDto> saleListSearchWord(SaleParamDto saleParamDto);
	int saleListSearchWordTotalCount(SaleParamDto saleParamDto);
	
	void saleInsert(SaleDto saleDto);
	void saleUpdate(SaleDto saleDto);
	void saleDelete(int no);

	List<SaleDto> saleListDealer(SaleParamDto saleParamDto);

	SaleDto saleDetail(int no);
}
