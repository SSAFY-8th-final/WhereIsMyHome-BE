package com.mycom.myhouse.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myboard.dto.BoardDto;
import com.mycom.myboard.dto.BoardResultDto;
import com.mycom.myhouse.admin.dao.EventDao;
import com.mycom.myhouse.admin.dto.EventParamDto;
import com.mycom.myhouse.admin.dto.EventResultDto;
import com.mycom.myhouse.event.dto.EventDto;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	EventDao dao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;
	
	@Override
	public EventResultDto eventList(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.eventList(eventParamDto);
			int count = dao.eventListTotalCount();
			eventResultDto.setList(list);
			eventResultDto.setCount(count);
			eventResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}
	
	@Override
	public EventResultDto boardListSearchWord(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.eventListSearchWord(eventParamDto);
			int count = dao.EventListSearchWordTotalCount(eventParamDto);
			eventResultDto.setList(list);
			eventResultDto.setCount(count);
			eventResultDto.setResult(SUCCESS);
			
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventDetail(EventParamDto eventDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			EventDto dto = dao.eventDetail(eventDto);
			
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventInsert(EventDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventResultDto eventUpdate(EventDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventResultDto eventDelete(int eventKey) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
