package com.mycom.myhouse.event.service;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

public interface EventService {
//	// limit, offset
//	EventResultDto eventList(EventParamDto eventDto);
//	// searchWord
//	EventResultDto boardListSearchWord(EventParamDto boardParamDto);
//		
//	EventResultDto eventDetail(EventParamDto eventDto);
//	EventResultDto eventInsert(EventDto dto);
//	EventResultDto eventUpdate(EventDto dto);
//	EventResultDto eventDelete(int eventKey);
	EventResultDto eventAttend(EventParamDto eventParamDto);

	EventResultDto eventList(EventParamDto eventParamDto);

	EventResultDto eventDetail(int eventKey);
}
