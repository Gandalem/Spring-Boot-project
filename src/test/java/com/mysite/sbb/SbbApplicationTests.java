package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {
	@Autowired	//객체 자동주입 , JPA의 CRUD할수있는 메소드가 적용되어 있음.
	private QuestionRepository questionRepository;
	
	/* 조건에 맞는 레코드 하나만 가져오기*/
	@Test
	public void jpaTestget() {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			Question q = oq.get();
			System.out.println(q.getId()+"\n"+q.getSubject()+"\n"+q.getContent());
		}
	}
	
	/*
	@Test
	public void jpaTest() {
		
		List<Question> all = this.questionRepository.findAll();
		assertEquals(6, all.size()); //assertEquals(기대값,실제값) 두값이 일치 할때 true
		
		Question q = all.get(0); //List all 변수에 담긴 0번방의 Question 객체를 끄집어넨다
		assertEquals("sbb가 무엇인가요?", q.getSubject());
		
		System.out.println(q.getId()+"\n"+q.getSubject()+"\n"+q.getContent()+"\n"+q.getCreateDate());
	}
	*/
	/*
	@Test
	void contextLoads() {
		 Question q1 = new Question();
		 
		 q1.setSubject("sbb가 무엇인가요?");
		 q1.setContent("sbb에 대해서 알고 싶습니다.");
		 q1.setCreateDate(LocalDateTime.now()); //현제 시간을 setter에 저장
		 this.questionRepository.save(q1);	//DB의 저장
		 
		 Question q2 = new Question();
		 
		 q2.setSubject("spring boot model 질문입니다?");
		 q2.setContent("id는 자동으로 생성되나요");
		 q2.setCreateDate(LocalDateTime.now()); //현제 시간을 setter에 저장
		 this.questionRepository.save(q2);	//DB의 저장
		 */
	

}
