package com.mycom.myhouse.user.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycom.myhouse.event.dto.EventDto;
import com.mycom.myhouse.event.dto.EventResultDto;
import com.mycom.myhouse.user.dao.UserDao;
import com.mycom.myhouse.user.dto.UserDto;
import com.mycom.myhouse.user.dto.UserResultDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao dao;

	private final int SUCCESS = 1;
	private final int FAIL = -1;

//	@Override
//	public List<UserDto> list() {
//		// TODO Auto-generated method stub
//		return dao.list();
//	}

	@Override
	public UserResultDto userDetail(String userEmail) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserDto userDto = dao.userDetail(userEmail);
			userResultDto.setUserDto(userDto);

			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto register(UserDto dto) {
		UserResultDto userResultDto = new UserResultDto();
		System.out.println("UserServiceImple - register");
		try {
			dao.register(dto);
			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	// @Transactional
	public UserResultDto userUpdate(UserDto dto) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			dao.userUpdate(dto);
			userResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public UserResultDto userDelete(UserDto userDto) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			String userEmail = userDto.getUserEmail();
			// 삭제 순서
	        dao.userEventAttendDelete(userEmail);	// 이벤트 참여 삭제
			dao.userDelete(userEmail); // 마지막으로 게시판 삭제

			userResultDto.setResult(SUCCESS);

		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
			// throw new RuntimeException();
		}

		return userResultDto;
	}

	@Override
	public UserResultDto login(UserDto dto) {
		UserResultDto userResultDto = new UserResultDto();

		try {
			UserDto userDto = dao.login(dto.getUserEmail());
			// userDto는 테이블에서 조회한 데이터가 포함
			// dto는 client가 전송한 데이터가 포함
			if (userDto != null && userDto.getUserPassword().equals(dto.getUserPassword())) {
				userResultDto.setUserDto(userDto);
				userResultDto.setResult(SUCCESS);
			}
		} catch (Exception e) {
			e.printStackTrace();
			userResultDto.setResult(FAIL);
		}
		return userResultDto;
	}

	@Override
	public EventResultDto userEventAttendList(String userEmail) {
		EventResultDto eventResultDto = new EventResultDto();
		try {
			List<EventDto> list = dao.userEventAttendList(userEmail);
			eventResultDto.setList(list);
			System.out.println(list);
			eventResultDto.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			eventResultDto.setResult(FAIL);
		}
		return eventResultDto;
	}

}
