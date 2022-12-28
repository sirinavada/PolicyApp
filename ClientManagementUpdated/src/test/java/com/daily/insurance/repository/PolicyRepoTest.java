package com.daily.insurance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.daily.insurance.model.Policy;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)

class PolicyRepoTest {

	@Autowired
	private PolicyRepo pRepo;

	private Policy p = new Policy();

	@BeforeEach
	public void init() {
		p.setPolicyId(1);
		p.setPolicyCoverage(10000.0);
		p.setPolicyName("Accident Cover");
		p.setPolicyPremium(100.0);
	}
	
	@Test
	void testFindPolicy() {
		Policy p1 = null;
		
		pRepo.save(p);
		
		p1 = pRepo.findById(p.getPolicyId()).get();
		assertEquals(p.getPolicyName(), p1.getPolicyName());
	}

}
