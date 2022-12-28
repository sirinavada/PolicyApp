package com.daily.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.daily.insurance.model.Wallet;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Integer>{
	@Query(value = "select * from wallet where wallet.user_id = :userId", nativeQuery = true)
	public Wallet findWalletByUserId(int userId);	
}
