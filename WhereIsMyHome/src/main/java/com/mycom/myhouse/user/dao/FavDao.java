package com.mycom.myhouse.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.user.dto.FavDto;

@Mapper
public interface FavDao {
	int insertFav(FavDto favDto);
	int removeFav(FavDto favDto);
	List<FavDto> getUserFav(FavDto favDto);
	int favListTotalCount(FavDto favDto);
}
