package com.mycom.myhouse.event.dao;

import java.util.List;

import com.mycom.myhouse.event.dto.EventDto;


public interface EventDao {
	int boardInsert(EventDto dto);
	int boardUpdate(EventDto dto); // 수정
	int boardDelete(int boardId); // 삭제

	List<EventDto> boardList(int limit, int offset);
	List<EventDto> boardListSearchWord(int limit, int offset, String searchWord);
	int boardListTotalCnt();
	int boardListSearchWordTotalCnt(String searchWord);
//	
	EventDto boardDetail(int boardId); // 상세
	
	List<EventDto> boardListCustomer(int limit, int offset);
	String boardPage(int eventKey);
	int boardAttend(int eventKey, String userId);
	
}
