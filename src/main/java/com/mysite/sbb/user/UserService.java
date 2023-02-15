package com.mysite.sbb.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository userRepository;
	//PasswordEncoder 를 @Bean 으로 등록하면 UserService 도 다음과 같이 수정할수 있다.
	private final PasswordEncoder passwordEncoder;
	
	public SiteUser create(String username, String email, String password) {
		
		SiteUser user = new SiteUser();
		
		user.setUsername(username);
		
		user.setEmail(email);
		
		//BCrypt 해싱 함수(BCrypt hashing function)를 사용해서 비밀번호를 암호화한다.
		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		
		user.setPassword(passwordEncoder.encode(password));
		
		this.userRepository.save(user);
		
		return user;
	}
	
}
