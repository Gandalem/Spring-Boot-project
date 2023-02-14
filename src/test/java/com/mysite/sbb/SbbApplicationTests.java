package com.mysite.sbb;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class SbbApplicationTests {
	@Autowired	//객체 자동주입 , JPA의 CRUD할수있는 메소드가 적용되어 있음.
	private QuestionRepository questionRepository;
	@Autowired //객체를 자동 주입 , 디펜던시 인젝션(DI) , JPA의 메소드를 사용, findAll(), findById(), save(), delete()
	private AnswerRepository answerRepository;
	
	/*Answer 테이블에 더미 데이터 입력*/
	
	@Test
	public void insertAnswer() {
		Question q =null;
		Answer a = new Answer();
		
		//Question 객체 질문에 대한 값을 가지고 와서 answer question필드에 넣어준다.
		Optional<Question> op = null;
		
		
			op = this.questionRepository.findById(2);
		
		q = op.get();
		
		a.setContent("2번 글에 대한 답변 입니다.-3");
		a.setCreateDate(LocalDateTime.now());
		a.setQuestion(q);
		this.answerRepository.save(a);
		
	}
	
	/*question 테이블에 for 문을 사용해서 더미값 1000개 insert*/
	/*
	@Test
	public void insert1000() {
		Question q = null;
		
		
		for(int i=0;i<1000;i++) {
			q = new Question();
			q.setSubject("제목-"+i);
			q.setContent("내용-"+i);
			q.setCreateDate(LocalDateTime.now());
			
			
			this.questionRepository.save(q);
		}
	}
	
	
	*/
	/*
	@Transactional //아래의 메소드가 하나의 트랜잭션으로 작동 되도록 설정 테스트 환경에서만 적용 
	@Test
	public void testjpa8() {
		//1. Question 테이블에서 질문의 레코드를 얻어온다. 끄집어 낸다.
		Optional<Question> op = this.questionRepository.findById(1);
		
		Question q = op.get();
		
		//2. 끄집어낸 객체의 q.getAnswerList();
		//Question 객체의 answerList 컬럼은 List<answer>
		List<Answer> all = q.getAnswerList();
		
		//3. 출력 구문에서 출력 한다.
		for(int i =0;i<all.size();i++) {
			Answer a = all.get(i);
			System.out.println(a.getId());
			System.out.println(a.getContent());
			System.out.println(a.getCreateDate());
			System.out.println(a.getQuestion());
			System.out.println("===========");
		}
	}
	
	
	
	*/
	/*답변 레코드 가져오기*/
	/*
	@Test
	public void testjpa7() {
		Optional<Answer> op = 
				this.answerRepository.findById(3);
		Optional<Question> op2 = 
				this.questionRepository.findById(1);
		if(op.isPresent()) {
			Answer a = op.get();
			Question b = op2.get();
			System.out.println(a.getId());
			System.out.println(a.getContent());
			System.out.println(a.getCreateDate());
			System.out.println(b.getId());
		}
	}
	*/
	
	
	/*
	@Test
	public void testAnswerjpa() {
		//1. Question (부모) 테이블의 답변을 처리할 레코드를 먼저 select 한다. findById(1)
		Optional<Question> oq = this.questionRepository.findById(1);
		Question q = oq.get();
		//2.Answer 엔티티 테이블의 객체 생성을 하고, setter 를 사용해서 값을 넣는다.
		Answer a = new Answer();
		a.setQuestion(q);
		a.setContent("3-수정된 내용의 대한 답변입니다");
		a.setCreateDate(LocalDateTime.now());
		//3. save 메소드를 사용해서 저장
		this.answerRepository.save(a);
	}
	
	*/
	/*데이터 삭제 : jpa 메소드 : delete()*/
	/*
	@Test
	public void testjpa6() {
		//1. 테이블에서 삭제할 레코드를 가지고 옮
		Optional<Question> op =
				this.questionRepository.findById(2);
		//2.optional 에 저장된 객체를 끄집어 낸다.
		Question q = op.get();
		//3.delete(q)
		this.questionRepository.delete(q);
	}
	*/
	
	
	
	/*데이터 수정 : save()*/
	/*
	@Test
	public void testjpa5() {
		//수정할 객체를 findById() 메소드를 사용해서 가지고 온다.
		Optional<Question> op =
				this.questionRepository.findById(1);
		//가지고온 객체를 끄집어 내서 setter 를 사용해서 수정
		Question q = op.get();
		q.setSubject("수정된 내용");
		q.setContent("수정된 내용");
		//3.수정된 객체를 save(객체) 메소드 안에 집어넣으면 updateQuery 
		this.questionRepository.save(q);
	}
	*/
	/*테이블의 모든 레코드 정렬*/
	/*
	@Test
	public void testjpa4() {
		List<Question> or1 = this.questionRepository.findAllByOrderByCreateDateAsc();
		List<Question> or2 = this.questionRepository.findAllByOrderByCreateDateDesc();
		
		System.out.println("=============");
		
		for(int i =0;i<or1.size();i++) {
			Question q = or1.get(i);
		
			System.out.println(q.getId());
			System.out.println(q.getSubject());
			System.out.println(q.getCreateDate());
		}
		System.out.println("================");
		
		for(Question q2 : or2) {
			System.out.println(q2.getId());
			System.out.println(q2.getSubject());
			System.out.println(q2.getContent());
		}
		*/
		/*
		for(Question q : or) {
			System.out.println(q);
		}
	}
		 */
	/*
	@Test //홈페이지 검색 기능
	public void testjpa() {
		//findBySubject(?) 메소드 questionRepository 만듬
		System.out.println("======subject====");
		List<Question> q = this.questionRepository.findBySubjectLike("%sbb%");
		Question oq = q.get(0);
		System.out.println("리스트에 검색된 레코드수 : "+q.size());
		System.out.println(oq.getId());
		System.out.println(oq.getSubject());
		System.out.println(oq.getContent());
		
		System.out.println("=======content=======");
		List<Question> o = this.questionRepository.findByContentLike("%id%");
		Question qo = o.get(0);
		System.out.println("리스트의 검색된 레코드 수"+o.size());
		System.out.println(qo.getId());
		System.out.println(qo.getSubject());
		System.out.println(qo.getContent());
		System.out.println("====Subject and Content====");
		List<Question> sc = this.questionRepository.findBySubjectLikeOrContentLike("%sbb%", "%싶습니다%");
		Question s = sc.get(0);
		System.out.println("리스트의 검색된 레코드 수"+sc.size());
		System.out.println(s.getId());
		System.out.println(s.getSubject());
		System.out.println(s.getContent());
	}
	*/
	
	
	/* 조건에 맞는 레코드 하나만 가져오기*/
	/*Question 테이블의 Primary key 컬럼은 기본적으로 제공됨 : findByid()*/
	/*
	@Test
	public void jpaTestget() {
		Optional<Question> oq = this.questionRepository.findById(1); //하나의 값을 가져올때는 optional , findByid() 조건을 걸어서 값을 가져옴
		if(oq.isPresent()) {
			Question q = oq.get();
			System.out.println(q.getId()+"\n"+q.getSubject()+"\n"+q.getContent());
		}
	}
	*/
	/*
	@Test
	public void jpaTest() {
		
		List<Question> all = this.questionRepository.findAll();
		//assertEquals(6, all.size()); //assertEquals(기대값,실제값) 두값이 일치 할때 true
		
		Question q = all.get(0); //List all 변수에 담긴 0번방의 Question 객체를 끄집어넨다
		//assertEquals("sbb가 무엇인가요?", q.getSubject());
		
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
	}
	*/

}
