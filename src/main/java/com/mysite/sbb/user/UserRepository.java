package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import lombok.Getter;

public interface UserRepository extends JpaRepository<SiteUser, Long> {
	//JPA는 SQL 쿼리를 사용하지 않고 JPA메소드가 SQL쿼리로 변환해서 처리
	//findAll()
	//findById()
	//save() <== Insert, Update
	//delete() <== delete
	
	Optional<SiteUser> findByusername(String username);
	
	@Getter
	public enum UserRole{
		ADMIN("ROLE_ADMIN"),
		USER("ROLE_USER");
		
		UserRole(String value){
			this.value = value;
		}
		
		private String value;
		
	}
}