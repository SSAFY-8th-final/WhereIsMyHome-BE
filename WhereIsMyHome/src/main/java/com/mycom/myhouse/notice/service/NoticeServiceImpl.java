package com.mycom.myhouse.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.admin.dao.AdminNoticeDao;
import com.mycom.myhouse.notice.dao.NoticeDao;
import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	NoticeDao dao;

	private final String SUCCESS = "success";
	private final String FAIL = "fail";
	
	@Override
	public NoticeResultDto noticeList(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<NoticeDto> list = dao.noticeList(noticeParamDto);
			int count = dao.noticeListTotalCount();
			
			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}
	
	@Override
	public NoticeResultDto noticeListSearchWord(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<NoticeDto> list = dao.noticeListSearchWord(noticeParamDto);
			int count = dao.noticeListSearchWordTotalCount(noticeParamDto);
			
			noticeResultDto.setList(list);
			noticeResultDto.setCount(count);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeDetail(NoticeParamDto noticeParamDto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			int userReadCount = dao.noticeUserReadCount(noticeParamDto);
			if(userReadCount == 0) {
				dao.noticeUserReadInsert(noticeParamDto.getNoticeId(), noticeParamDto.getUserSeq());
				dao.noticeReadCountUpdate(noticeParamDto.getNoticeId());
			}
			
			NoticeDto dto = dao.noticeDetail(noticeParamDto);
			noticeResultDto.setDto(dto);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}
}
