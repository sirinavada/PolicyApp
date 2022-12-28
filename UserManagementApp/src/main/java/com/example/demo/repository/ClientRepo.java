package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;


@Repository
public interface ClientRepo extends JpaRepository<User,Integer>{	
	
	@Query(value = "select * from user where user_name = :username", nativeQuery = true)
	public User findClient(String username);	
	
}
