package com.daily.insurance.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Policy {
	@Id
	private int policyId;
	private String policyName;
	private Double policyPremium;
	private Double policyCoverage;
}
