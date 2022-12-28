package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserPolicyClaim {

	@Id
	@GeneratedValue
	private int policyClaimId;
	private String purchaseDttm;
	private Double claimAmount;
	private String claimStatus;
	private String claimDttm;
	private String policyName;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
