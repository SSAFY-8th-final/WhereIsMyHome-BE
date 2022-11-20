package com.mycom.myhouse.notice.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;

@Mapper
public interface NoticeDao {

	List<NoticeDto> noticeList(NoticeParamDto noticeParamDto);
	int noticeListTotalCount();
	
	List<NoticeDto> noticeListSearchWord(NoticeParamDto noticeParamDto);
	int noticeListSearchWordTotalCount(NoticeParamDto noticeParamDto);
	
	// detail
	NoticeDto noticeDetail(NoticeParamDto noticeParamDto);
	
	// 조회수
	int noticeUserReadCount(NoticeParamDto noticeParamDto); 
	int noticeUserReadInsert(
			@Param("noticeId") int noticeId, 
			@Param("userSeq") int userSeq ); 
	int noticeReadCountUpdate(int noticeId);
}
