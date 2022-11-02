package com.mycom.myhouse.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mycom.myhouse.common.LoginInterceptor;


@Configuration  // xml 설정을 대신 <- servlet-context.xml의 설정 내용을 대체, interceptor외에 다른 많은 설정 가능
public class WebMvcConfig implements WebMvcConfigurer {
	
	// interceptor에 대한 정책
	// interceptor 객체 DI
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")  // 로그인을 해야지만 접근 가능
				.excludePathPatterns("/login.html", "/register.html")
				.excludePathPatterns("/login/**", "/register/**", "/css/**", "/js/**", "/img/**");
	}
	
}

// interface는 추상메소드만으로 구성, 바디를 가지는 default 메소드를 가질 수 없었음
// interface에 가령 20개의 추상 메소드가 있으면, 그걸 implements 하는 클래스는 딱 2개만 구현하고 싶은데 18개를 다 구현해야 하는 경우 (빈 바디)
// api에서 자주 사용되는 interface에 대해서는 클래스에 대해 xxxAdaptor를 제공
// xxxAdaptor 클래스는 interface의 20개 메소드를 스스로 모두 구현(빈 바디) 함. -> 원하는 것만 오버라이딩 할 수 있게 됨 !!
// => A interface를 implement를 하는 것이 아니고, extends A Adaptor해서 2개만 오버라이딩하면 되도록 함