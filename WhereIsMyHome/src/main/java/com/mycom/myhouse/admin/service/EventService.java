package com.mycom.myhouse.admin.service;

import com.mycom.myhouse.admin.dto.EventDto;
import com.mycom.myhouse.admin.dto.EventParamDto;
import com.mycom.myhouse.admin.dto.EventResultDto;

public interface EventService {
	
	// limit, offset
	EventResultDto eventList(EventParamDto eventDto);
	// searchWord
	EventResultDto boardListSearchWord(EventParamDto boardParamDto);
		
	EventResultDto eventDetail(EventParamDto eventDto);
	EventResultDto eventInsert(EventDto dto);
	EventResultDto eventUpdate(EventDto dto);
	EventResultDto eventDelete(int eventKey);
}
