package com.daily.insurance.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.daily.insurance.exceptions.PolicyException;
import com.daily.insurance.model.Policy;
import com.daily.insurance.model.PolicyPurchaseRequest;
import com.daily.insurance.model.PurchaseResponse;
import com.daily.insurance.model.UserPolicyClaim;
import com.daily.insurance.model.UserPolicyClaimRequest;
import com.daily.insurance.model.Wallet;
import com.daily.insurance.model.WalletResponse;
import com.daily.insurance.model.WalletTopupRequest;
import com.daily.insurance.service.PolicyServiceImpl;

@RestController
@CrossOrigin("*")
public class PolicyController {

	@Autowired
	PolicyServiceImpl policyService;

	private static final Logger logger = LogManager.getLogger(PolicyController.class);

	@PostMapping("/add-wallet-balance")
	public WalletResponse addWalletBalance(@RequestBody WalletTopupRequest request) {
		try {
			return policyService.addAmount(request);
		} catch (PolicyException e) {
			logger.error("Exception adding amount to wallet", e);
			return null;
		}
	}

	@PostMapping("/purchase-policy")
	public PurchaseResponse purchasePolicy(@RequestBody PolicyPurchaseRequest req) {
		try {
			return policyService.purchasePolicy(req);
		} catch (PolicyException e) {
			logger.error("Exception occured while purchasing the policy", e);
			return null;
		}
	}

	@GetMapping("/purchased-policy/{policyName}")
	public Policy findPurchasedPolicy(@PathVariable("policyName") String policyName) {
		logger.info("Policy Name in getting policy details: {}", policyName);
		return policyService.findPolicyByPolicyName(policyName);
	}

	@PutMapping("/process-claim")
	public WalletResponse claimPolicy(@RequestBody UserPolicyClaimRequest req) {
		return policyService.processClaim(req);
	}

	@GetMapping("/policyInfo/{userId}")
	public List<UserPolicyClaim> getPolicyById(@PathVariable("userId") int userId) {
		logger.info("user Id in getting policy details: {}", userId);
		return policyService.findPolicyClaimByUserId(userId);
	}

	@GetMapping("/all-policies")
	public List<Policy> getAllPolicy() {
		return policyService.getAllPolicy();
	}

	@GetMapping("/wallet-info/{userId}")
	public Wallet findWalletByUserId(@PathVariable("userId") int userId) {
		return policyService.findWalletByUserId(userId);
	}

}
