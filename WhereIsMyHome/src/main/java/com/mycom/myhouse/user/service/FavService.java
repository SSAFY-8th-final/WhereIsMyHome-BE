package com.mycom.myhouse.user.service;

import com.mycom.myhouse.user.dto.FavDto;
import com.mycom.myhouse.user.dto.FavResultDto;

public interface FavService {
	FavResultDto insertFav(FavDto favDto);
	FavResultDto removeFav(FavDto favDto);
	FavResultDto getUserFav(FavDto favDto);
}
