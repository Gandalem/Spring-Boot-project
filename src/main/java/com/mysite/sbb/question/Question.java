package com.mysite.sbb.question;

import java.time.LocalDateTime; //자신의 시스템의 로케일의 시간설정
import java.util.List;
import java.util.Set;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity //자바 클래스를 DataBase의 테이블과 매핑된 클래스 : 테이블명 : question bean등록 을 해준다
public class Question {
	
	@Id//primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //시퀀스 할당 초기값 1 증감값 1
	private int id; //primary Key ,시퀀스 (1,1)
	
	@Column(length = 200) //200자까지
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime createDate;
	
	/*
	@Column(length = 200) //200자까지
	private String addr;
	*/
	
	//question 테이블에서 Answer 테이블을 참조하는 컬럼을 생성 @onetoMany
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
	
	@ManyToOne
	private SiteUser author;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter; //한명의 사용자가 여러 질문에 투표 할수 있다.
						 //하나의 질문에 여러명의 사용자가 투표할수 있다. 1번만 투표 가능하도록 test
	
	//List : 방의 번호(index)를 가지고 중복된 값을 저장할수있다.
	//set : 자료형은 중복된 갑ㅆ을 넣을수 없는 자료형
		//set은 방번호를 가지지 않는다.
}
