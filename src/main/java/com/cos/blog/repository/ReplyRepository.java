package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cos.blog.dto.ReplySaveRequestDto;
import com.cos.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{
	
	@Modifying
	@Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(:userId, :boardId, :content, now())", nativeQuery = true)
	int mSave(@Param("userId") int userId, @Param("boardId") int boardId, @Param("content") String content);
	}
