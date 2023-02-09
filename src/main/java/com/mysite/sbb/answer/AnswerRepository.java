package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<엔티티가들어있는 클래스, PK가 들어있는 컬럼 타입>
public interface AnswerRepository extends JpaRepository<Answer, Integer> {
	//JAP 에서 사용 가능한 메소드 상속됨
		//findAll()
		//findById()
		//save()
		//delete()
	
}
