package com.mycom.myhouse.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.admin.dao.AdminEventDao;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

@Service
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	AdminEventDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
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
			eventResultDto.setDto(dto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventInsert(EventDto dto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			dao.eventInsert(dto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventUpdate(EventDto dto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			dao.eventUpdate(dto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventDelete(int eventKey) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			dao.eventDelete(eventKey);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}
}
