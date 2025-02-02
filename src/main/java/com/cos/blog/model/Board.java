package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.*;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity  // User클래스가 Mysql에 테이블이 자동으로 생성이된다.
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob //대용량 대이터
	private String content;	//섬머노트 라이브러리 <html> 태그가 섞여서 디자인이 됨.
	
	private int count; //조회수
	
	@ManyToOne(fetch = FetchType.EAGER) //Many = Board, User = One
	@JoinColumn(name="userId")
	private User user;
	
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) //mappedBy 연관관계의 주인이 아니다. (난 FK가 아니에요) DB에 칼럼을 만들지 마세요.
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private Timestamp createDate;
	
	
	
}
