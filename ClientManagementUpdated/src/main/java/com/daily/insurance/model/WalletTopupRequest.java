package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletTopupRequest {
	private int userId;
	private String mode;
	private Double amount;	
}
