package com.daily.insurance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.daily.insurance.model.Wallet;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class WalletRepoTest {

	@Autowired
	WalletRepo wRepo;

	Wallet w = new Wallet();
	
	@BeforeEach
	private void init() {
		w.setMode("Debit Card");
		w.setAmount(1000.0);
		w.setWalletId(1);
	}
	@Test
	void testFindWalletByUserId() {
		wRepo.save(w);
		assertEquals("Debit Card", w.getMode());
	}

}
