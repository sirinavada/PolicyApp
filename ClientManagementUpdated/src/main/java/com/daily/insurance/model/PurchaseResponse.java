package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResponse {
	private int policyClaimId;
	private String purchaseDttm;
	private Double claimAmount;
	private String claimStatus;
	private String claimDttm;
	private String policyName;
	private String errorMessage;
}
