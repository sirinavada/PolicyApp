package com.daily.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daily.insurance.model.Policy;

@Repository
public interface PolicyRepo extends JpaRepository<Policy, Integer> {
	@Query(value = "select * from policy where policy_name = :policyName", nativeQuery = true)
	public Policy findPolicy(String policyName);
}
