package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;
//사용자가 요청 -> 응답(data) 
//@RestController

//사용자 요청 -> 응답(HTML파일)
//@Controller
@RestController
public class HttpControllerTest {
	
	private static final String TAG = "HttpControllerTest:";
	
	@GetMapping("http/lombok")
	public String lombokTest() {
		Member m = Member.builder().username("ssar").password("1234").email("xogur").build();
		System.out.println(TAG + "getter:" +m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG + "setter:" +m.getId());
		return "lombok test완료";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m) {
		
		return "get요청 : " +m.getId() + ", "+m.getUsername() + ", "+m.getPassword()+ ", "+m.getEmail();
	}
	
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) {
		return "post요청 :"+m.getId() + ", "+m.getUsername() + ", "+m.getPassword()+ ", "+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청" +m.getId() + ", "+m.getUsername() + ", "+m.getPassword()+ ", "+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete요청";
	}

}
