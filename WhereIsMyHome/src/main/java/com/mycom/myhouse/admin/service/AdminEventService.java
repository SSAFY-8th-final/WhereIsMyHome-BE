package com.mycom.myhouse.admin.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

public interface AdminEventService {
	
	// limit, offset
	EventResultDto eventList(EventParamDto eventParamDto);
	// searchWord
	EventResultDto eventListSearchWord(EventParamDto eventParamDto);
	// option
	EventResultDto eventListOption(EventParamDto eventParamDto);
	// searchWord & option
	EventResultDto eventListSearchWordOption(EventParamDto eventParamDto);
		
	EventResultDto eventDetail(EventParamDto eventParamDto);
	EventResultDto eventInsert(EventDto dto, MultipartHttpServletRequest request);
	EventResultDto eventUpdate(EventDto dto, MultipartHttpServletRequest request);
	EventResultDto eventDelete(int eventKey);
}
