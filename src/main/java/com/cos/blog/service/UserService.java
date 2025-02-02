package com.cos.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.config.auth.PrincipalDetail;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private HttpServletRequest request;
	
	@Transactional(readOnly = true)
	public User 회원찾기(String username) {
		User user = userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}
	
	
	@Transactional
	public void 회원가입(User user) {
		String rawPassword = user.getPassword(); //1234원문
		String encPassword = encoder.encode(rawPassword); // 해쉬
		user.setPassword(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
		
		PrincipalDetail principalDetail = new PrincipalDetail(user);
		// UsernamePasswordAuthenticationToken을 사용하여 인증 객체 생성
        Authentication authentication = new UsernamePasswordAuthenticationToken(principalDetail, principalDetail.getPassword(), principalDetail.getAuthorities());
        
        // 인증 정보를 SecurityContext에 설정
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 세션에 SecurityContext 저장
        HttpSession session = request.getSession();
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
	}
	
//	@Transactional(readOnly = true)
//	public User 로그인(User user) {
//		return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//	}
	
	@Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(()-> {
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		
	}
	
	
	
}
