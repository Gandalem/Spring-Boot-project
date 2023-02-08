package com.mysite.sbb;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter				//필드에 대한 getter를 자동으로 생성해줌
@Setter				//필드에 대한 setter를 자동으로 생성해줌
@NoArgsConstructor	//기본생성자를 자동으로 생성해줌
@ToString			//객체 자체를 출력시 자동으로 필드의 내용을 출력
public class HelloLombok {

	private String hello;
	private int lombok;
	
	
	public static void main(String[] args) {
		
		//위에 생선된 클래스의 내용이 롬복이 잘 적용되었는지 확인
		//객체 생성 <== 객체 내부의 필드와 메소드를 사용함
		HelloLombok lombok = new HelloLombok();
		lombok.setHello("Hello");
		lombok.setLombok(1);
		
		System.out.println(lombok.getHello()+" "+lombok.getLombok());
		
		System.out.println(lombok);
	}

}
