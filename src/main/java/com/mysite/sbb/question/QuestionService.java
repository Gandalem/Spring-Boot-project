package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DateNotFoundExceptoin;
import com.mysite.sbb.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // DI (생성자 객체 주입)
@Service
public class QuestionService {
	//jpa 메소드를 사용하기 위해 (생성자를 이용한 객체 자동 주입)
	private final QuestionRepository questionRepository;
	
	//메소드 : question 테이블의 List 정보를 가지고오는 메소드
	/*	<2월 14일 수정됨 : paging 처리를 위해
	public List<Question> getList() {
		return this.questionRepository.findAll();
	}
	*/
	public Page<Question> getList(int page){
		
		//최신글을 먼저 출력 하기, 날짜 컬럼 (createDate) 을 desc 해서 출력
		List<Sort.Order> sorts = new ArrayList();
		sorts.add(Sort.Order.desc("createDate"));
		
		//Pageable 객체에 두개의 값을 담아서 매개변수로 던짐 : 0,1,2,3
		Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));
		
		return this.questionRepository.findAll(pageable);
	}
	
	
	//상세 페이지를 처리하는 메소드 : id를 받아서 Question 테이블을 select (findById(1))
		//해서 select 한 레코드를 question 객체레 담아서 리턴
	public Question getQuestion(Integer id) {
		
		//select * from question where id = ?
		Optional<Question> op = this.questionRepository.findById(id);
		if(op.isPresent()) { //op에 값이 존재하는경우
			return op.get(); //question 객체를 리턴
		}else {
			//사용자 정의 예외
			//throw : 예외를 강제롤 발생
			//throws : 예외를 요청한 곳에서 처리하도록 미루는것
			throw new DateNotFoundExceptoin("요청한 파일을 찾지 못했습니다.");
		}
	}
	public void save(String subject,String content, SiteUser user) {
		
		Question q = new Question();
		
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setAuthor(user);
		
		this.questionRepository.save(q);
	}
	
	public void modify(Question question, String subject, String content) {
		 question.setSubject(subject);
		 question.setContent(content);
		 question.setModifyDate(LocalDateTime.now());
		 this.questionRepository.save(question);
		 }
	
	public void delete(Question question) {
		 this.questionRepository.delete(question);
		 }
	
	
}
