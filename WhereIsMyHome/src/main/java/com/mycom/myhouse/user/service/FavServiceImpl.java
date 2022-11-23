package com.mycom.myhouse.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.user.dao.FavDao;
import com.mycom.myhouse.user.dto.FavDto;
import com.mycom.myhouse.user.dto.FavResultDto;

@Service
public class FavServiceImpl implements FavService {

	@Autowired
	FavDao dao;
	
	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@Override
	public FavResultDto insertFav(FavDto favDto) {
		FavResultDto favResultDto = new FavResultDto();
		
		try {
			int ret = dao.insertFav(favDto);
			if(ret == 1) {
				favResultDto.setResult(SUCCESS);
			} else {
				favResultDto.setResult(FAIL);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			favResultDto.setResult(FAIL);
		}
		return favResultDto;
	}

	@Override
	public FavResultDto removeFav(FavDto favDto) {
		FavResultDto favResultDto = new FavResultDto();
		
		try {
			int ret = dao.removeFav(favDto);
			if(ret == 1) {
				favResultDto.setResult(SUCCESS);
			} else {
				favResultDto.setResult(FAIL);				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			favResultDto.setResult(FAIL);
		}
		return favResultDto;
	}

	@Override
	public FavResultDto getUserFav(FavDto favDto) {
		FavResultDto favResultDto = new FavResultDto();
		
		try {
			List<FavDto> list = dao.getUserFav(favDto);
			int count = dao.favListTotalCount(favDto);
			favResultDto.setList(list);
			favResultDto.setCount(count);
			
			favResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			favResultDto.setResult(FAIL);
		}
		return favResultDto;
	}


}
