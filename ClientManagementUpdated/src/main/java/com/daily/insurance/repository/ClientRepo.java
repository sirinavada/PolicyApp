package com.daily.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daily.insurance.model.User;

@Repository
public interface ClientRepo extends JpaRepository<User,Integer>{	
	
	@Query(value = "select * from user where user_name = :username", nativeQuery = true)
	public User findClient(String username);	
	
}
