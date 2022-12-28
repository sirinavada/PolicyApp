package com.daily.insurance.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.daily.insurance.model.User;
import com.daily.insurance.model.UserPolicyClaim;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserPolicyClaimRepoTest {
	@Autowired
	private UserPolicyClaimRepo cRepo;

	private UserPolicyClaim c = new UserPolicyClaim();

	private User u = new User();

	private static final Logger log = LogManager.getLogger(UserPolicyClaimRepoTest.class);

	@BeforeEach
	private void init() {
		c.setClaimAmount(100000.0);
		c.setClaimDttm("10/06/2022 17:25:33");
		c.setClaimStatus("Eligible");
		c.setPolicyClaimId(1);
		c.setPolicyName("Accident Cover");
		c.setPurchaseDttm("10/06/2022 17:25:33");
		u.setId(1);
		c.setUser(u);
	}

	@Test
	void testFindPurchasedPolicy() {
		log.info("User.getuserId: {}", c.getUser().getId());
		cRepo.save(c);
		List<UserPolicyClaim> c1 = cRepo.findPurchasedPolicy(c.getUser().getId());
		log.info("c1 policy: {}", c1.get(0).getPolicyName());
		assertNotNull(c1);
	}

	@Test
	void testFindPolicyForClaim() {
		cRepo.save(c);
		UserPolicyClaim c1 = cRepo.findPolicyForClaim(c.getPolicyName(), c.getPurchaseDttm());
		log.info("c policy name: {}", c.getPolicyName());
		assertEquals(c1.getPolicyName(), c.getPolicyName());
	}

}
