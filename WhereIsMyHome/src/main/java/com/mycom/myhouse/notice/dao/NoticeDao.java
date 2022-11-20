package com.mycom.myhouse.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {

	List<NoticeDto> noticeList();
	int noticeListTotalCount();
	
	List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto);
	int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto);
	
	// detail
	NoticeDto noticeDetail(NoticeParamDto noticeParamDto);
}
