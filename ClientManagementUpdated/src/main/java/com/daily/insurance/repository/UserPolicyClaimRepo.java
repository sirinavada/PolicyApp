package com.daily.insurance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daily.insurance.model.UserPolicyClaim;

@Repository
public interface UserPolicyClaimRepo extends JpaRepository<UserPolicyClaim, Integer> {
	@Query(value = "select * from user_policy_claim where user_id = :userId", nativeQuery = true)
	public List<UserPolicyClaim> findPurchasedPolicy(int userId);

	@Query(value = "select * from user_policy_claim where policy_name = :policyName and purchase_dttm = :purchaseDttm", nativeQuery = true)
	public UserPolicyClaim findPolicyForClaim(String policyName, String purchaseDttm);
}
