package com.mysite.sbb.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	//JPA는 SQL 쿼리를 사용하지 않고 JPA메소드가 SQL쿼리로 변환해서 처리
	//findAll()
	//findById()
	//save() <== Insert, Update
	//delete() <== delete
}
