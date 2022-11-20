package com.mycom.myhouse.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;

@Mapper
public interface AdminNoticeDao {

	List<NoticeDto> noticeList(NoticeParamDto noticeParamDto);
	int noticeListTotalCount();
	
	List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto);
	int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto);
	
	// detail
	NoticeDto noticeDetail(NoticeParamDto noticeParamDto);
	
	// insert
	int noticeInsert(NoticeDto dto);
	
	// update
	int noticeUpdate(NoticeDto dto);
	
	// delete
	int noticeDelete(int noticeId);
	int noticeReadCountDelete(int noticeId);
}
