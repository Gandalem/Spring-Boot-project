package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
	//JPA에서 Question 테이블을 Select, Insert, Update, Delete
	//JPA의 CRUD 메소드 : 
		//save() : Insert,Update,delete
		//findAll()
		//findById()
		//정의해서 사용 : findBySubject() ......
	
	
	
	//Question 테이블을 SQL 쿼리를 사용하지 않고 JPA 메소드를 사용해서 CRUD 하는 Repository
		//JpaRepository<Qustion , Integer>
			//Question : JPA 메소드에서 쿼리할 엔티티 클래스
			//Integer : 엔티티 클래스의 primary key 컬럼의 데이터 타입
	
	//하나의 컬럼을 조건으로 처리된 값 가져오기 : PK 컬럼은 findById(?) 기본 제공
	//findById() : Question 테이블의 Primary Key 컬럼이므로 기본 제공됨
	//find(where) By(where) Id(SQL 컬럼)
	//select * from Question where subject = ? 검색결과가 하나일때 사용함
	Question findBySubject(String subject); 
	//select * from Question where content = ? 검색결과가 하나일때 사용함
	Question findByContent(String content);
	
	//select * from question where subject like '%sbb%' 검색 결과가 여러게일 때 사용함
	List<Question> findBySubjectLike(String subject);
	
	//select * from question where content like '%sbb%' 검색 결과가 여러게일 때 사용함
	List<Question> findByContentLike(String content);
	
	//select * from question where subject like '%sbb%' and content like '%내용%'
	List<Question> findBySubjectLikeOrContentLike(String subject,String content);
	
	//정렬해서 출력 : Order By
	//select * from question order by createDate asc : 오름 차순
	//select * from question order by createDate desc : 내림차순
	List <Question> findAllByOrderByCreateDateAsc();
	List <Question> findAllByOrderByCreateDateDesc();
	
	//Update : save()
	
	//delete : delete()
	
	//페이징을 처리하기 위한 메소드 생성 
	//select * from question : pageable 변수에 : page, 레코드수를 넣어주면
	//출력할 레코드수를 jpa에 알려주면 내부에서 jpa가 전체 레코드(1000개) / 10개 = 100 페이지
	Page<Question> findAll(Pageable pageable);
	
	Page<Question> findAll(Specification<Question> spec, Pageable pageable);
	
	
}
