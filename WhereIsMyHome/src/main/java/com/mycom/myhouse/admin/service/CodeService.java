package com.mycom.myhouse.admin.service;

import java.util.List;

import com.mycom.myhouse.admin.dto.CodeDto;

public interface CodeService {
	List<CodeDto> codeList(String parentCode);
}
