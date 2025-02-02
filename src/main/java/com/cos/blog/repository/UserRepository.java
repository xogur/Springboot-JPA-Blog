package com.cos.blog.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Integer>{
	// select * from user where username = 1?;
	Optional<User> findByUsername(String username);
}


//User findByUsernameAndPassword(String username, String password);

//@Query(value="SELECT * FROM user WHERE username = ?1AND password = ?2", nativeQuery = true)
//User login(String username, String password);