package com.mycom.myhouse.event.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventParamDto;

@Mapper
public interface EventDao {
	//int boardInsert(EventDto dto);
	//int boardUpdate(EventDto dto); // 수정
	//int boardDelete(int boardId); // 삭제

	//List<EventDto> boardList(int limit, int offset);
	//List<EventDto> boardListSearchWord(int limit, int offset, String searchWord);
	//int boardListTotalCnt();
	//int boardListSearchWordTotalCnt(String searchWord);
//	
	//EventDto boardDetail(int boardId); // 상세
	
	List<EventDto> eventListUser(int limit, int offset);
	//String boardPage(int eventKey);
	//int eventAttend(int eventKey, String userEmail);

	//void eventAttend(EventResultDto eventResultDto);

	EventDto eventDetail(int eventKey);
	void eventAttend(EventParamDto eventParamDto);

	List<EventDto> eventList(EventParamDto eventParamDto);

	
}
