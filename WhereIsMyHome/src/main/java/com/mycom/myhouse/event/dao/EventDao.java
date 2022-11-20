package com.mycom.myhouse.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.event.dto.EventParamDto;

@Mapper
public interface EventDao {
	// 이벤트 전체목록
	List<EventDto> eventList(EventParamDto eventParamDto);
	List<EventFileDto> eventListFile(int eventKey);
	
//	// 이벤트 검색 목록 -> 이미지는 eventListFile로 들고와 boardId와 일치하는 것만 연결
//	List<EventDto> eventListSearchWord(EventParamDto eventParamDto);
//	int eventListSearchWordTotalCount(EventParamDto eventParamDto);

	// 이벤트 상세
	EventDto eventDetail(int eventKey);
	List<EventFileDto> eventDetailFileList(int eventKey);
	int attendCount(int eventKey);
	int isAttend(EventParamDto eventParamDto);
	
	// 조회수
	int eventUserReadCount(EventParamDto eventParamDto); 
	int eventUserReadInsert(
			@Param("eventKey") int eventKey, 
			@Param("userSeq") int userSeq ); 
	int eventReadCountUpdate(int eventKey);
	
	// 이벤트 참여
	void eventAttend(EventParamDto eventParamDto);
	// 이벤트 참여취소
	void leaveEvent(EventParamDto eventParamDto);
}
