package com.mycom.myhouse.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.sale.dto.SaleDto;
import com.mycom.myhouse.user.dto.FavDto;

@Mapper
public interface FavDao {
	int insertFav(FavDto favDto);
	int removeFav(FavDto favDto);
	int[] getUserFav(FavDto favDto);
	List<SaleDto> saleDetail(int no);
	int favListTotalCount(FavDto favDto);
}
