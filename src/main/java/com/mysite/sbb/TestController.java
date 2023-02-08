package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		String test = "test코드 블락-mybatis - 추가된내용 -111";
		
		return test;
	}
}
