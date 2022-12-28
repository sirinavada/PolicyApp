package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolicyPurchaseRequest {
	private String policyName;
	private int userId;
}
