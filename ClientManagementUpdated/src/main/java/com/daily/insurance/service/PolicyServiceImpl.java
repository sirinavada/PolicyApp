package com.daily.insurance.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daily.insurance.exceptions.PolicyException;
import com.daily.insurance.model.Constants;
import com.daily.insurance.model.Policy;
import com.daily.insurance.model.PolicyPurchaseRequest;
import com.daily.insurance.model.PurchaseResponse;
import com.daily.insurance.model.User;
import com.daily.insurance.model.UserPolicyClaim;
import com.daily.insurance.model.UserPolicyClaimRequest;
import com.daily.insurance.model.Wallet;
import com.daily.insurance.model.WalletResponse;
import com.daily.insurance.model.WalletTopupRequest;
import com.daily.insurance.repository.ClientRepo;
import com.daily.insurance.repository.PolicyRepo;
import com.daily.insurance.repository.UserPolicyClaimRepo;
import com.daily.insurance.repository.WalletRepo;

@Service
public class PolicyServiceImpl implements PolicyService {

	@Autowired
	WalletRepo walletRepo;
	@Autowired
	ClientRepo userRepo;
	@Autowired
	PolicyRepo policyRepo;
	@Autowired
	UserPolicyClaimRepo claimRepo;

	private static final Logger log = LogManager.getLogger(PolicyServiceImpl.class);

	@Override
	public WalletResponse addAmount(WalletTopupRequest req) throws PolicyException {
		try {
			if (userRepo.findById(req.getUserId()).isPresent()) {
				Wallet w = walletRepo.findWalletByUserId(req.getUserId());
				if (w != null) {
					w.setAmount(w.getAmount() + req.getAmount());
					w.setMode(req.getMode());
					User user = userRepo.findById(req.getUserId()).get();
					user.setWallet(w);
					w.setUser(user);
					userRepo.saveAndFlush(user);
					walletRepo.saveAndFlush(w);
					WalletResponse res = new WalletResponse();
					res.setAmount(w.getAmount());
					res.setWalletId(w.getWalletId());
					return res;
				} else {
					log.info("wallet is empty. adding new wallet");
					Wallet wallet = new Wallet();
					wallet.setMode(req.getMode());
					wallet.setAmount(req.getAmount());
					User u = userRepo.findById(req.getUserId()).get();
					wallet.setUser(u);
					walletRepo.save(wallet);
					userRepo.saveAndFlush(u);
					WalletResponse res = new WalletResponse();
					res.setAmount(wallet.getAmount());
					res.setWalletId(wallet.getWalletId());
					return res;
				}

			}
			throw new PolicyException("User is null. Cannot add wallet");
		} catch (PolicyException e) {
			WalletResponse res = new WalletResponse();
			res.setErrorMessage(e.getMessage());
			return res;
		} catch (Exception e) {
			WalletResponse res = new WalletResponse();
			res.setErrorMessage("Exception while purchasing policy");
			return res;
		}

	}

	@Override
	public PurchaseResponse purchasePolicy(PolicyPurchaseRequest req) throws PolicyException {
		log.info("inside purchase policy");
		Policy policy = policyRepo.findPolicy(req.getPolicyName());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		log.info("policy purchased: {}", policy);
		if (processPayment(policy, req.getUserId())) {
			List<UserPolicyClaim> claimList = claimRepo.findPurchasedPolicy(req.getUserId());
			UserPolicyClaim claim = new UserPolicyClaim();
			User user = userRepo.findById(req.getUserId()).get();
			claim.setPurchaseDttm(date);
			claim.setClaimStatus(Constants.CLAIM_ELIGIBLE.toString());
			claim.setPolicyName(policy.getPolicyName());
			claimList.add(claim);
			claim.setUser(user);
			userRepo.save(user);
			claimRepo.saveAndFlush(claim);
			PurchaseResponse res = new PurchaseResponse();
			res.setPolicyName(claim.getPolicyName());
			res.setPurchaseDttm(claim.getPurchaseDttm());
			res.setPolicyClaimId(claim.getPolicyClaimId());
			return res;
		}
		return null;
	}

	private boolean processPayment(Policy policy, int userId) {
		log.info("inside process payment");
		Wallet wallet = walletRepo.findWalletByUserId(userId);
		log.info("wallet amt: {}", wallet.getAmount());
		log.info("policy premium amt: {}", policy.getPolicyPremium());
		if (wallet.getAmount() >= policy.getPolicyPremium()) {
			wallet.setAmount(wallet.getAmount() - policy.getPolicyPremium());
			log.info("wallet amout set after purchase: {}", wallet.getAmount());
			walletRepo.save(wallet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public WalletResponse processClaim(UserPolicyClaimRequest req) {
		log.info("req policy name: {}", req.getPolicyName());
		log.info("req policy purchase dttm: {}", req.getPurchaseDttm());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		log.info("req policy formatted purchase dttm: {}", req.getPurchaseDttm());
		UserPolicyClaim claim = claimRepo.findPolicyForClaim(req.getPolicyName(), req.getPurchaseDttm());
		if (claim != null) {
			log.info("claim from db fetched ");
		} else {
			log.info("claim empty");
		}
		try {
			if (claim != null && (claim.getClaimStatus() == null
					|| claim.getClaimStatus().equalsIgnoreCase(Constants.CLAIM_ELIGIBLE.toString()))) {
				Policy policy = policyRepo.findPolicy(claim.getPolicyName());
				Wallet wallet = walletRepo.findWalletByUserId(claim.getUser().getId());
				claim.setClaimAmount(policy.getPolicyCoverage());
				claim.setClaimStatus(Constants.CLAIM_COMPLETE.toString());
				claim.setClaimDttm(date);
				wallet.setAmount(wallet.getAmount() + policy.getPolicyCoverage());
				walletRepo.saveAndFlush(wallet);
				claimRepo.saveAndFlush(claim);
				WalletResponse res = new WalletResponse();
				res.setAmount(wallet.getAmount());
				res.setWalletId(wallet.getWalletId());
				return res;
			} else {
				throw new PolicyException("User doesnot have any policy or the claim has been availed");
			}
		} catch (PolicyException e) {
			WalletResponse res = new WalletResponse();
			res.setErrorMessage("User doesnot have any policy or the claim has been availed");
			return res;
		} catch (Exception e) {
			WalletResponse res = new WalletResponse();
			res.setErrorMessage("Exception while purchasing policy");
			return res;
		}
	}

	@Override
	public List<UserPolicyClaim> findPolicyClaimByUserId(int userId) {
		return claimRepo.findPurchasedPolicy(userId);
	}

	@Override
	public Policy findPolicyByPolicyName(String policyName) {
		log.info("Policy Name : {}", policyName);
		return policyRepo.findPolicy(policyName);
	}

	@Override
	public Wallet findWalletByUserId(int userId) {
		return walletRepo.findWalletByUserId(userId);
	}

	public List<Policy> getAllPolicy() {
		return policyRepo.findAll();
	}

}
