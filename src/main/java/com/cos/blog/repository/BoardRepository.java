package com.cos.blog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;
import com.cos.blog.model.User;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Integer>{
	
}

