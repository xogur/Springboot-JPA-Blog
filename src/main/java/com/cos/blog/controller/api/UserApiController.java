package com.cos.blog.controller.api;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;


@RestController
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/auth/joinProc")
	public ResponseDto<Integer> save(@RequestBody User user, HttpServletRequest request) {
		System.out.println("UserApiController : save 호출됨");
		
		userService.회원가입(user);
		
		//자동 로그인
		PrincipalDetail principalDetail = new PrincipalDetail(user);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(
	            principalDetail, 
	            principalDetail.getPassword(), 
	            principalDetail.getAuthorities()
	    );
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		HttpSession session = request.getSession();
	    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) {
//		System.out.println("UserApiController : login호출됨");
//		User principal = userService.로그인(user); //principal (접근주체)
//		
//		if(principal != null) {
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
	
	@PutMapping("/user")
	public ResponseDto<Integer> update(@RequestBody User user) {
		
		userService.회원수정(user);
		
		//세션등록
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
}
