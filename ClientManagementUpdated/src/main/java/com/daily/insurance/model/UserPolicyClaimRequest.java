package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPolicyClaimRequest {

	private String policyName;
	private String purchaseDttm;
	private Double claimAmount;
}
