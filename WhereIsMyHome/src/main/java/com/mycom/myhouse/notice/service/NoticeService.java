package com.mycom.myhouse.notice.service;

import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;

public interface NoticeService {

	// limit, offset
	NoticeResultDto noticeList(NoticeParamDto noticeParamDto);
	// searchWord
	NoticeResultDto noticeListSearchWord(NoticeParamDto NoticeParamDto);
		
	NoticeResultDto noticeDetail(NoticeParamDto NoticeParamDto);
	
}
