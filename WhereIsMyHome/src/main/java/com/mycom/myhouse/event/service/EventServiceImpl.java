package com.mycom.myhouse.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.event.dao.EventDao;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dao.UserDao;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	EventDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@Override
	public EventResultDto eventList(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();

		try {
			List<EventDto> list = dao.eventList(eventParamDto);	//limit, offset
			eventResultDto.setList(list);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}
	
	@Override
	public EventResultDto eventDetail(int eventKey) {
		EventResultDto eventResultDto = new EventResultDto();

		try {
			EventDto eventDto = dao.eventDetail(eventKey);
			eventResultDto.setDto(eventDto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}

	@Override
	public EventResultDto eventAttend(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();

		try {
			dao.eventAttend(eventParamDto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}

}
