package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 스프링의 환경설정 파일을 의미하는 어노테이션 : @EnableWebSecurity 모든요청 URL이 스프링 시큐리티의 제어를 받도록 만든다
@EnableWebSecurity // 내부적으로 SpringSecurityFilterChain이 동작하혀 필터가 적용된다
					// 보안의 세부적인 설정은 SecurityFilterChain 빈을 생성하여 설정할수있다.
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		// AntPathRequestMatcher:사용자가 요청한 요청정보를 확인하여 요청정보 Url이 /**으로 시작하는지 확인한다.
		// **모든 요청과 일치하는 범용 일치로 처리됩니다.

		http.authorizeHttpRequests().requestMatchers(new AntPathRequestMatcher("/**")).permitAll() // 모든 URL을 허용
				.and() // http 객체의 설정을 이어서 할 수 있게 하는 메서드이다
				// H2 콘솔은 이와 같은 CSRF 토큰을 발행하는 기능이 없기 때문에 위와 같은 403 오류가 발생하는 것이다.
				// ignoringRequestMatchers:특정요청 에만 대상을 제외
				.csrf().ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")) // h2-console을 예외 처리
				.and()
				.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
						//frame 에 포함된 페이지가 페이지를제공하는 사이트와 동일한 경우에는 계속 사용할 수 있다.
						XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
				//Spring Security 로그인 처리부분
				.and()
					.formLogin()
					.loginPage("/user/login") //로그인페이지 지정
					.defaultSuccessUrl("/") //성공했을때 이동 페이지 로그인 성공기 세션에 로그인 정보를 담고. 페이지로 이동
				//Spring Security 로그아웃 처리 부분
				.and()
					.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
					.logoutSuccessUrl("/") //성공했을때 이동 페이지
					.invalidateHttpSession(true); //세션의 모든값을 삭제 해하
						

		return http.build();
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		
		 return new BCryptPasswordEncoder();
		 
		 }
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
