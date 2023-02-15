package com.mysite.sbb.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity //DB랑 연결된 클래스다 선언
public class SiteUser {//예약어의 존재로 SiteUser 이름 차용
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)//유일한 값만 저장할 수 있음을 의미한다.
	private String username;
	
	private String password;
	
	@Column(unique = true)
	private String email;
}
