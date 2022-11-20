package com.mycom.myhouse.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.event.dto.EventParamDto;


@Mapper
public interface AdminEventDao {

	// limit, offset
	List<EventDto> eventList(EventParamDto eventDto);
	int eventListTotalCount();
	
	// searchWord
	List<EventDto> eventListSearchWord(EventParamDto eventParamDto);
	int eventListSearchWordTotalCount(EventParamDto eventParamDto);
	
	// option
	List<EventDto> eventListOption(EventParamDto eventParamDto);
	int eventListOptionTotalCount(EventParamDto eventParamDto);
	
	// searchword & option
	List<EventDto> eventListSearchWordOption(EventParamDto eventParamDto);
	int eventListSearchWordOptionTotalCount(EventParamDto eventParamDto);
	
	// detail
	EventDto eventDetail(EventParamDto eventParamDto);
	List<EventFileDto> eventDetailFileList(int eventKey); // 파일 추가
	
	// insert
	int eventInsert(EventDto dto);
	int eventFileInsert(EventFileDto dto);
	
	// update
	int eventUpdate(EventDto dto);
	
	// delete
	int eventDelete(int eventKey);
	int eventFileDelete(int eventKey);
	List<String> eventFileUrlDeleteList(int eventKey);
	int eventReadCountDelete(int eventKey);
	
}
