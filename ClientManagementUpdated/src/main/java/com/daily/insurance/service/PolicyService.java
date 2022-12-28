package com.daily.insurance.service;

import java.util.List;

import com.daily.insurance.exceptions.PolicyException;
import com.daily.insurance.model.Policy;
import com.daily.insurance.model.PolicyPurchaseRequest;
import com.daily.insurance.model.PurchaseResponse;
import com.daily.insurance.model.UserPolicyClaim;
import com.daily.insurance.model.UserPolicyClaimRequest;
import com.daily.insurance.model.Wallet;
import com.daily.insurance.model.WalletResponse;
import com.daily.insurance.model.WalletTopupRequest;

public interface PolicyService {

	public WalletResponse addAmount(WalletTopupRequest req) throws PolicyException;

	public PurchaseResponse purchasePolicy(PolicyPurchaseRequest req) throws PolicyException;

	public WalletResponse processClaim(UserPolicyClaimRequest req) throws PolicyException;
	
	public List<UserPolicyClaim> findPolicyClaimByUserId(int userId);

	Policy findPolicyByPolicyName(String policyName);

	Wallet findWalletByUserId(int userId);
}
