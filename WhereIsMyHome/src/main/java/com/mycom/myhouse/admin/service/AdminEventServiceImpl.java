package com.mycom.myhouse.admin.service;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mycom.myhouse.admin.dao.AdminEventDao;
import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventFileDto;
import com.mycom.myhouse.event.dto.EventParamDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dto.UserDto;

@Service
public class AdminEventServiceImpl implements AdminEventService {

	@Autowired
	AdminEventDao dao;
	
	@Value("${app.fileupload.uploadDir}")
	String uploadFolder;
	
	@Value("${app.fileupload.uploadPath}")
	String uploadPath;

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
	public EventResultDto eventListSearchWord(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.eventListSearchWord(eventParamDto);
			int count = dao.eventListSearchWordTotalCount(eventParamDto);
			
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
	public EventResultDto eventListOption(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.eventListOption(eventParamDto);
			int count = dao.eventListOptionTotalCount(eventParamDto);
			
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
	public EventResultDto eventListSearchWordOption(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			// 목록, 총건수를 가져온다.
			List<EventDto> list = dao.eventListSearchWordOption(eventParamDto);
			int count = dao.eventListSearchWordOptionTotalCount(eventParamDto);
			
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
	public EventResultDto eventDetail(EventParamDto eventParamDto) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			EventDto dto = dao.eventDetail(eventParamDto);
			List<EventFileDto> fileList = dao.eventDetailFileList(dto.getEventKey());
			dto.setFileList(fileList);
			
			if(dto.getRegisterId().equals(eventParamDto.getUserEmail())) {
				dto.setSameUser(true);
			} else {
				dto.setSameUser(false);
			}
			
			eventResultDto.setDto(dto);
			
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventInsert(EventDto dto, MultipartHttpServletRequest request) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			dao.eventInsert(dto);
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);  // 업로드된 파일이 저장된 폴더
			if(!uploadDir.exists()) uploadDir.mkdir();  // 없으면 새로 생성
			
			List<MultipartFile> fileList = request.getFiles("file");
			
			for (MultipartFile partFile : fileList) {
				int eventKey = dto.getEventKey();  // 직전 등록된 게시글의 key
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래파일 명. 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID();  // 대체될 파일이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);
				
				EventFileDto eventFileDto = new EventFileDto();
				eventFileDto.setEventKey(eventKey);
				eventFileDto.setFileName(fileName);
				eventFileDto.setFileSize(partFile.getSize());
				eventFileDto.setFileContentType(partFile.getContentType());
				eventFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // /는 웹의 경로
				
				dao.eventFileInsert(eventFileDto);
			}
			
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
			
			throw new RuntimeException();
		}
		
		return eventResultDto;
	}

	@Override
	public EventResultDto eventUpdate(EventDto dto, MultipartHttpServletRequest request) {
		EventResultDto eventResultDto = new EventResultDto();
		
		try {
			dao.eventUpdate(dto);
			
			File uploadDir = new File(uploadPath + File.separator + uploadFolder);  // 업로드된 파일이 저장된 폴더
			if(!uploadDir.exists()) uploadDir.mkdir();  // 없으면 새로 생성
			
			// 기존 첨부된 물리적인 파일 삭제, 첨부파일이 여러개 감안
			List<String> fileUrlList = dao.eventFileUrlDeleteList(dto.getEventKey());
			for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}
			
			// 테이블에서 게시판 파일 삭제
			dao.eventFileDelete(dto.getEventKey());
			
			// 수정과정에서 새로 추가된 첨부 파일 등록
			List<MultipartFile> fileList = request.getFiles("file");
			
			// NullPointer 예외발생 -> 트랜잭션 처리
			// @Transactional 달기
//			String str = null;
//			str.length();
			
			for (MultipartFile partFile : fileList) {
				int eventKey = dto.getEventKey();  // 직전 등록된 게시글의 key
				String fileName = partFile.getOriginalFilename(); // 첨부된 원래파일 명. 이 이름으로는 바로 저장하지 않고 UUID를 이용해서 중복불가한 파일 이름을 만든다.
				
				// Random UUID File id
				UUID uuid = UUID.randomUUID();  // 대체될 파일이름
				
				// 파일 확장자
				String extension = FilenameUtils.getExtension(fileName); // 원래 파일의 확장자만 추출
				
				// 실제 저장할 파일 전체 이름은
				String savingFileName = uuid + "." + extension;
				
				File destFile = new File(uploadPath + File.separator + uploadFolder + File.separator + savingFileName);
				
				// 파일 객체를 통해서 파일을 저장
				partFile.transferTo(destFile);
				
				EventFileDto eventFileDto = new EventFileDto();
				eventFileDto.setEventKey(eventKey);
				eventFileDto.setFileName(fileName);
				eventFileDto.setFileSize(partFile.getSize());
				eventFileDto.setFileContentType(partFile.getContentType());
				eventFileDto.setFileUrl(uploadFolder + "/" + savingFileName); // /는 웹의 경로
				
				dao.eventFileInsert(eventFileDto);
			}
			
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
			// 기존 첨부된 물리적인 파일 삭제, 첨부파일이 여러개 감안
			List<String> fileUrlList = dao.eventFileUrlDeleteList(eventKey);
			for (String fileUrl : fileUrlList) {
				File file = new File(uploadPath + File.separator + fileUrl);
				if(file.exists()) {
					file.delete();
				}
			}
			
			// 삭제 순서 (외래키 고려)
			dao.eventFileDelete(eventKey); // 첨부파일 삭제
			dao.eventReadCountDelete(eventKey); // 조회수 삭제
			dao.eventDelete(eventKey); // 게시글 삭제
			
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		
		return eventResultDto;
	}

}
