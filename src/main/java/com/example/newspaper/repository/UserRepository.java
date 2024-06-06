package com.example.newspaper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.newspaper.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
	@Query(value = "select * from user where username = :username and password = :password", nativeQuery = true)
	public User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
	
	User findByUsername(String username);

}
