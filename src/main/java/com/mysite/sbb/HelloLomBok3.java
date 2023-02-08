package com.mysite.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor //객체 생성시 모든필드의 값을 입력 받아 필드의 초기값을 할당
public class HelloLomBok3 {

	private String hello;
	private int lombok;
	
	//public HelloLomBok3() {} <== 기본생성자
	/*public HelloLomBok3(String hello, int lombok) { <= @AllArgsConstructor
	 	this.hello = hello;
	 	this.lombok = lombok;.
	
	}
	*/
	public static void main(String[] args) {
		// @AllArgsConstructor 테스트
		
		HelloLomBok3 lombok3 = new HelloLomBok3("안녕",1);
		
		System.out.println(lombok3.getHello()+"\n"+lombok3.getLombok());
		
		System.out.println(lombok3);

	}

}
