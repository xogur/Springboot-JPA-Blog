package com.cos.blog.model;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity  // User클래스가 Mysql에 테이블이 자동으로 생성이된다.
//@DynamicInsert //inset시에 null을 주석처리 해준다
public class User {
	
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 100, unique = true)
	private String username; // 아이디
	
	@Column(nullable = false, length = 100)
	private String password;
	
	@Column(length = 50)
	private String email;
	
//	@ColumnDefault("'user'")
	@Enumerated(EnumType.STRING)
	private RoleType role; //Enum을 쓰는게 좋다. //admin,user,manager
	
	private String oauth;
	
	@CreationTimestamp//시간이 자동으로 입력
	private Timestamp createDate;
}
