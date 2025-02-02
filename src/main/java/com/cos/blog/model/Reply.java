package com.cos.blog.model;

import jakarta.persistence.Entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reply {
	@Id //primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 db의 넘버링 전략을 따라간다.
	private int id; // 시퀀스, auto_increment
	
	@Column(nullable = false, length = 200)
	private String content;
	
	@ManyToOne
	@JoinColumn(name="boardId")
	private Board board;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	@CreationTimestamp
	private LocalDateTime createDate;

	@Override
	public String toString() {
		return "Reply [id=" + id + ", content=" + content + ", board=" + board + ", user=" + user + ", createDate="
				+ createDate + "]";
	}

}
