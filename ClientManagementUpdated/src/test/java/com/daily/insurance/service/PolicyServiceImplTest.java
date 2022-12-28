package com.daily.insurance.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.daily.insurance.exceptions.PolicyException;
import com.daily.insurance.model.Policy;
import com.daily.insurance.model.PolicyPurchaseRequest;
import com.daily.insurance.model.PurchaseResponse;
import com.daily.insurance.model.User;
import com.daily.insurance.model.UserPolicyClaim;
import com.daily.insurance.model.Wallet;
import com.daily.insurance.repository.ClientRepo;
import com.daily.insurance.repository.PolicyRepo;
import com.daily.insurance.repository.UserPolicyClaimRepo;
import com.daily.insurance.repository.WalletRepo;

@AutoConfigureMockMvc
@SpringBootTest
class PolicyServiceImplTest {

	private static final Logger log = LogManager.getLogger(PolicyServiceImplTest.class);

	@Mock
	private PolicyRepo policyRepo;

	@Mock
	private ClientRepo userRepo;

	@Mock
	private WalletRepo walletRepo;

	@Mock
	private UserPolicyClaimRepo claimRepo;

	@InjectMocks
	private PolicyServiceImpl clientService;

	@Test
	void testAddAmount() {
		User user = new User();
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");
		user.setId(1);

		Policy p = new Policy();
		p.setPolicyId(1);
		p.setPolicyCoverage(10000.0);
		p.setPolicyName("Accident Cover");
		p.setPolicyPremium(100.0);

		Wallet res = new Wallet();
		res.setAmount(1000.0);
		res.setWalletId(1);

		User u = userRepo.save(user);
		log.info("user repo find id 1: {}", u);
		policyRepo.save(p);
		walletRepo.save(res);

		when(walletRepo.findWalletByUserId(1)).thenReturn(res);
		assertEquals(1000.0, res.getAmount());

		when(userRepo.saveAndFlush(any())).thenReturn(user);
		assertEquals("raymond", user.getUserName());

		when(walletRepo.save(any())).thenReturn(res);
		assertEquals(1000.0, res.getAmount());

		when(walletRepo.saveAndFlush(any())).thenReturn(res);
		assertEquals(1000.0, res.getAmount());
	}

	@Test
	void testPurchasePolicy() throws PolicyException {

		List<UserPolicyClaim> claimList = new ArrayList<>();
		UserPolicyClaim res = new UserPolicyClaim();
		res.setClaimAmount(100000.0);
		res.setClaimDttm("10/06/2022 17:25:33");
		res.setClaimStatus("Eligible");
		res.setPolicyClaimId(1);
		res.setPolicyName("Accident Cover");
		res.setPurchaseDttm("10/06/2022 17:25:33");
		User u = new User();
		u.setId(1);
		res.setUser(u);

		claimList.add(res);

		Policy p = new Policy();
		p.setPolicyId(1);
		p.setPolicyCoverage(10000.0);
		p.setPolicyName("Accident Cover");
		p.setPolicyPremium(100.0);

		User user = new User();
		user.setFirtName("Raymond");
		user.setLastName("Holt");
		user.setEmailId("raymond@gmail.com");
		user.setUserName("raymond");
		user.setPassword("Raymond@1");
		user.setId(1);

		when(claimRepo.findPurchasedPolicy(1)).thenReturn(claimList);
		assertNotNull(claimList);

		when(policyRepo.findPolicy("Accident Cover")).thenReturn(p);
		assertEquals(100.0, p.getPolicyPremium());

		when(userRepo.save(any())).thenReturn(user);
		assertEquals("raymond", user.getUserName());

		when(claimRepo.saveAndFlush(any())).thenReturn(res);
		assertEquals("Accident Cover", res.getPolicyName());

		PolicyPurchaseRequest req = new PolicyPurchaseRequest();
		req.setPolicyName("Accident Cover");
		req.setUserId(1);

		PurchaseResponse r = new PurchaseResponse();
		r.setClaimAmount(100000.0);
		r.setClaimDttm("10/06/2022 17:25:33");
		r.setClaimStatus("Eligible");
		r.setPolicyClaimId(1);
		r.setPolicyName("Accident Cover");
		r.setPurchaseDttm("10/06/2022 17:25:33");
		r.setErrorMessage(null);

		Wallet wall = new Wallet();
		wall.setAmount(1000.0);
		wall.setMode("Debit Card");
		wall.setWalletId(1);

		when(walletRepo.findWalletByUserId(1)).thenReturn(wall);
		assertEquals("Debit Card", wall.getMode());

	}

	@Test
	void testProcessClaim() {
//		fail("Not yet implemented");
	}

	@Test
	void testFindPolicyClaimByUserId() {
//		fail("Not yet implemented");
	}

	@Test
	void testFindPolicyByPolicyName() {
//		fail("Not yet implemented");
	}

	@Test
	void testFindWalletByUserId() {
//		fail("Not yet implemented");
	}

	@Test
	void testGetAllPolicy() {
//		fail("Not yet implemented");
	}

}
