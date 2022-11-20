package com.mycom.myhouse.admin.service;

import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;

public interface AdminNoticeService {

	// limit, offset
	NoticeResultDto noticeList(NoticeParamDto noticeParamDto);
	// searchWord
	NoticeResultDto noticeListSearchWord(NoticeParamDto NoticeParamDto);
		
	NoticeResultDto noticeDetail(NoticeParamDto NoticeParamDto);
	NoticeResultDto noticeInsert(NoticeDto dto);
	NoticeResultDto noticeUpdate(NoticeDto dto);
	NoticeResultDto noticeDelete(int noticeId);
}
