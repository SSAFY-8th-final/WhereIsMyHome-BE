package com.mycom.myhouse.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.admin.dto.CodeDto;

@Mapper
public interface CodeDao {
	List<CodeDto> codeList(String code);

}
