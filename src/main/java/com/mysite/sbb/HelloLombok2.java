package com.mysite.sbb;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class HelloLombok2 {
	
	private int seq;
	private String title;
	private String writer;
	private String content;
	private Date regdate;
	private int cnt;
	
	
	
	public static void main(String[] args) {
		// 객체 생성후 테스트
		HelloLombok2 lombok = new HelloLombok2();
		Date date = new Date(123,1,06);
		
		lombok.setSeq(20);
		lombok.setTitle("제목입니다");
		lombok.setWriter("이순신");
		lombok.setContent("내용입니다");
		lombok.setRegdate(date);
		lombok.setCnt(0);
		
		//getter를 사용해서 lombok2객체에 저장된 메모리 필드의 값을 출력
		System.out.println(lombok.getSeq()+"\n"+lombok.getTitle()+"\n"+lombok.getWriter()+""
				+ "\n"+lombok.getContent()+"\n"+lombok.getRegdate()+"\n"+lombok.getCnt());
		//tostring () 메소드 호출 : 자체를 print
		System.out.println(lombok);

	}

}
