package com.daily.insurance.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.daily.insurance.model.Policy;
import com.daily.insurance.model.PurchaseResponse;
import com.daily.insurance.model.User;
import com.daily.insurance.model.UserPolicyClaim;
import com.daily.insurance.model.Wallet;
import com.daily.insurance.model.WalletResponse;
import com.daily.insurance.service.PolicyServiceImpl;

@AutoConfigureMockMvc
@SpringBootTest
class PolicyControllerTest {

	@Mock
	private PolicyServiceImpl pService;

	@InjectMocks
	private PolicyController pController;

	@Test
	void testAddWalletBalance() {
		WalletResponse res = new WalletResponse();
		res.setAmount(1000.0);
		res.setErrorMessage(null);
		res.setWalletId(1);

		when(pController.addWalletBalance(any())).thenReturn(res);
		assertEquals(1000.0, res.getAmount());
	}

	@Test
	void testPurchasePolicy() {
		PurchaseResponse res = new PurchaseResponse();
		res.setClaimAmount(100000.0);
		res.setClaimDttm("10/06/2022 17:25:33");
		res.setClaimStatus("Eligible");
		res.setPolicyClaimId(1);
		res.setPolicyName("Accident Cover");
		res.setPurchaseDttm("10/06/2022 17:25:33");
		res.setErrorMessage(null);

		when(pController.purchasePolicy(any())).thenReturn(res);
		assertEquals("Eligible", res.getClaimStatus());
	}

	@Test
	void testFindPurchasedPolicy() {
		Policy p = new Policy();
		p.setPolicyId(1);
		p.setPolicyCoverage(10000.0);
		p.setPolicyName("Accident Cover");
		p.setPolicyPremium(100.0);

		when(pController.findPurchasedPolicy(any())).thenReturn(p);
		assertEquals("Accident Cover", p.getPolicyName());
	}

	@Test
	void testClaimPolicy() {
		WalletResponse res = new WalletResponse();
		res.setAmount(1000.0);
		res.setErrorMessage(null);
		res.setWalletId(1);

		when(pController.claimPolicy(any())).thenReturn(res);
		assertEquals(1000.0, res.getAmount());
	}

	@Test
	void testGetPolicyById() {
		List<UserPolicyClaim> claimList = new ArrayList<>();
		UserPolicyClaim c = new UserPolicyClaim();
		c.setClaimAmount(100000.0);
		c.setClaimDttm("10/06/2022 17:25:33");
		c.setClaimStatus("Eligible");
		c.setPolicyClaimId(1);
		c.setPolicyName("Accident Cover");
		c.setPurchaseDttm("10/06/2022 17:25:33");
		User u = new User();
		u.setId(1);
		c.setUser(u);

		claimList.add(c);

		when(pController.getPolicyById(1)).thenReturn(claimList);
		assertNotNull(claimList);
	}

	@Test
	void testGetAllPolicy() {
		List<Policy> policyList = new ArrayList<>();
		Policy p = new Policy();
		p.setPolicyId(1);
		p.setPolicyCoverage(10000.0);
		p.setPolicyName("Accident Cover");
		p.setPolicyPremium(100.0);
		policyList.add(p);

		when(pController.getAllPolicy()).thenReturn(policyList);
		assertNotNull(policyList);
	}

	@Test
	void testFindWalletByUserId() {
		Wallet res = new Wallet();
		res.setAmount(1000.0);
		res.setWalletId(1);

		when(pController.findWalletByUserId(1)).thenReturn(res);
		assertEquals(1000.0, res.getAmount());
	}

}
