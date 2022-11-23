package com.mycom.myhouse.sale.service;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.map.dto.HouseDto;
import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

public interface SaleService {
//	// limit, offset

	SaleResultDto saleInsert(SaleDto saleDto);
	SaleResultDto saleDelete(int no);

	SaleResultDto saleList(SaleParamDto saleParamDto);
	SaleResultDto saleListSearchWord(SaleParamDto saleParamDto);
	
	SaleResultDto saleListDealer(SaleParamDto saleParamDto);

	SaleResultDto saleDetail(int no);
	SaleResultDto saleUpdate(SaleDto saleDto);

	SaleResultDto houseSearchByAddress(HouseDto dto);
	SaleResultDto houseInsert(HouseDto dto);
}
