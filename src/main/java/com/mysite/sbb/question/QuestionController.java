package com.mysite.sbb.question;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor //final 필드의 생성자를 자동으로 만들어서 생성자를 
@Controller //bean 등록
public class QuestionController {
	/* 클래스를 객체로 생성 어노테이션 (빈(객체)등록),Spring Framework)
		@component : 일반적인 클래스를 객체화
		@controller : 클라이언트 요청을 받아서 처리 , controller
			1. 클라이언트 요청을 받는다. @GetMapping, @PostMapping
			2. 비즈니스 로직 처리, Service의 메소드 호출,
			3. View 페이지로 응답
		
		@service : DAO의 메소드를 인터페이스로 생성 후 인터페이스의 메소드를 구현한 클래스
			- 유지보수를 쉽게 하기 위해서 (약결함)
		@repository : DAO 클래스를 빈등록
	
	*/
	/*	DI(의존성 주입)
	 	1. @Autowired : spring Framework 의 생성된 빈(객체)을 주입, 타입을 찾아서 주입
	 		같은 타입의 객체가 존재할 경루 문제가 발생될수 있다.
	 	2.생성자를 통한 의존성 주입(권장방식)
	 	3.Setter 를 사용한 의존성 주입
	 */
	
	//생성자를 통한 의존성 주입 <== 권장하는 방식
	@Autowired
	private final QuestionRepository questionRepository;
	
	
	@GetMapping("/question/list") //http://localhost:9292/question/list
	@PostMapping("/question/list")//from 태그의 method=post action = "/question/list"
	//@ResponseBody //요청을 브라우져에 찍어라
	public String list(Model model) {
		
		//1. 클라이언트 요청 정보 : http://localhost:9292/question/list
		
		//2. 비즈니스 로직 처리
		List<Question> questionList = 
				this.questionRepository.findAll();
		
		//3. 뷰(view) 페이지로 전송
			//model : 뷰페이지로 서버의 데이터를 담아서 전송 객체 (Session,Application)
		model.addAttribute("questionList", questionList);
		
		return "question_list";
	}
}
