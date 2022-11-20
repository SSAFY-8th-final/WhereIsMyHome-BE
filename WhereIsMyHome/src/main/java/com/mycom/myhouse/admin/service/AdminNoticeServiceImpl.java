package com.mycom.myhouse.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhouse.admin.dao.AdminNoticeDao;
import com.mycom.myhouse.notice.dto.NoticeDto;
import com.mycom.myhouse.notice.dto.NoticeParamDto;
import com.mycom.myhouse.notice.dto.NoticeResultDto;

@Service
public class AdminNoticeServiceImpl implements AdminNoticeService {
	
	@Autowired
	AdminNoticeDao dao;

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
			NoticeDto dto = dao.noticeDetail(noticeParamDto);
			noticeResultDto.setDto(dto);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeInsert(NoticeDto dto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		System.out.println(dto);
		
		try {
			dao.noticeInsert(dto);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
			
			throw new RuntimeException();
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeUpdate(NoticeDto dto) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			dao.noticeUpdate(dto);
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}

	@Override
	public NoticeResultDto noticeDelete(int noticeId) {
		NoticeResultDto noticeResultDto = new NoticeResultDto();
		
		try {
			dao.noticeDelete(noticeId); // 게시글 삭제
			
			noticeResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			noticeResultDto.setResult(FAIL);
		}
		
		return noticeResultDto;
	}
}
