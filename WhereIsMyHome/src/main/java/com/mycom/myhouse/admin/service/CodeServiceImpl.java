package com.mycom.myhouse.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.admin.dao.CodeDao;
import com.mycom.myhouse.admin.dto.CodeDto;

@Service
public class CodeServiceImpl implements CodeService{
	
	@Autowired
	CodeDao dao;
	
	@Override
	public List<CodeDto> codeList(String parentCode) {
		return dao.codeList(parentCode);
	}

}
