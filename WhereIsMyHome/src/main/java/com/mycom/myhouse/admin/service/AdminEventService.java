package com.mycom.myhouse.admin.service;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

public interface AdminEventService {
	
	// limit, offset
		EventResultDto eventList(EventParamDto eventDto);
		// searchWord
		EventResultDto boardListSearchWord(EventParamDto boardParamDto);
			
		EventResultDto eventDetail(EventParamDto eventDto);
		EventResultDto eventInsert(EventDto dto);
		EventResultDto eventUpdate(EventDto dto);
		EventResultDto eventDelete(int eventKey);
		
}
