package com.mycom.myhouse.event.service;

import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

public interface EventService {

	EventResultDto eventAttend(EventParamDto eventParamDto);
	EventResultDto leaveEvent(EventParamDto eventParamDto);

	EventResultDto eventList(EventParamDto eventParamDto);

	EventResultDto eventDetail(EventParamDto eventParamDto);
}
