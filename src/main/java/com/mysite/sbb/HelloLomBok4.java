package com.mysite.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor //생성자 생성시 필드 이름앞에 final 이들러있는 필드만 아규먼트로 생성
public class HelloLomBok4 {
	
	private final String hello;
	private int lombok;
	
	/* @RequiredArgsConstructor
		public hellolombok4(String hello) {
			this.hello = hello;
		}
	*/

	public static void main(String[] args) {
		HelloLomBok4 lombok4 = new HelloLomBok4("안녕");
		
		System.out.println(lombok4.getHello());
		System.out.println(lombok4);

	}

}
