package com.mycom.myhouse.event.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.event.dao.EventDao;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;

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
			List<EventFileDto> fileList = dao.eventListFile(eventParamDto.getEventKey());
			if(fileList.size() > 0) {
				eventResultDto.getDto().setFileList(fileList);				
			}
			eventResultDto.setList(list);
			
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}
	
	@Override
	public EventResultDto eventDetail(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();

		try {
			int attendCount = dao.attendCount(eventParamDto.getEventKey());
			int userReadCount = dao.eventUserReadCount(eventParamDto);
			if(userReadCount == 0) {
				dao.eventUserReadInsert(eventParamDto.getEventKey(), eventParamDto.getUserSeq());
				dao.eventReadCountUpdate(eventParamDto.getEventKey());
			}
			
			EventDto eventDto = dao.eventDetail(eventParamDto.getEventKey());
			
			int isAttend = dao.isAttend(eventParamDto); // userEmail
			if(isAttend == 0)
				eventDto.setAttend(false);
			else
				eventDto.setAttend(true);
			
			List<EventFileDto> fileList = dao.eventDetailFileList(eventParamDto.getEventKey());
			
			eventDto.setAttendCount(attendCount);
			eventDto.setFileList(fileList);
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
	
	@Override
	public EventResultDto leaveEvent(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();

		try {
			dao.leaveEvent(eventParamDto);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}

}
