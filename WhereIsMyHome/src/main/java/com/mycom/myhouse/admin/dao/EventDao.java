package com.mycom.myhouse.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.admin.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventDto;

@Mapper
public interface EventDao {

	// limit, offset
	List<EventDto> eventList(EventParamDto eventDto);
	int eventListTotalCount();
	
	// searchWord
	List<EventDto> eventListSearchWord(EventParamDto boardParamDto);
	int EventListSearchWordTotalCount(EventParamDto boardParamDto);
	
	// detail
	EventDto eventDetail(EventParamDto eventDto);
	
	int eventInsert(EventDto dto);
	int eventUpdate(EventDto dto);
	int eventDelete(int eventKey);
	
}
