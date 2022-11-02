package com.mycom.myhouse.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 비동기 요청에 대한 처리가 아닌 단순 페이지 이동
@Controller
public class HomeController {
	
	@GetMapping(value = "/")
	public String home() {
		return "/index.html"; // static folder의 index.html로 이동
	}
	
	// interceptor에 적용되지 않음 > prehandle x
	@GetMapping(value = "/login")
	public String login() {
		return "/login.html";
	}
	
	// interceptor에 적용되지 않음 > prehandle x
	@GetMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login.html";
	}
	
	// interceptor에 적용되지 않음 > prehandle x
	@GetMapping(value = "/register")
	public String register() {
		return "/register.html";
	}
	
	@GetMapping(value = "/map")
	public String map() {
		return "/map/mapMain.html";
	}

	@GetMapping(value = "/parking")
	public String parking() {
		return "/map/mapParking.html";
	}

	@GetMapping(value = "/event")
	public String event() {
		return "/event/eventMain.html";
	}

	@GetMapping(value = "/user")
	public String user() {
		return "/user/userMain.html";
	}
}
