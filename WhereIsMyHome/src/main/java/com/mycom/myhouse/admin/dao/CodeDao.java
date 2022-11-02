package com.mycom.myhouse.admin.dao;

import java.util.List;

import com.mycom.myhouse.admin.dto.CodeDto;

public interface CodeDao {
	List<CodeDto> getCodeList(String code);

}
