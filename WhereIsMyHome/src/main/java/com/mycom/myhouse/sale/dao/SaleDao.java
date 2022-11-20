package com.mycom.myhouse.sale.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.sale.dto.SaleParamDto;
import com.mycom.myhouse.sale.dto.SaleResultDto;

@Mapper
public interface SaleDao {
	void saleInsert(SaleDto saleDto);
	void saleUpdate(SaleDto saleDto);
	void saleDelete(int no);

	List<SaleDto> saleListDealer(SaleParamDto saleParamDto);

	SaleDto saleDetail(int no);
}
