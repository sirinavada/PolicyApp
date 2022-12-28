package com.daily.insurance.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WalletResponse {
	private int walletId;
	private Double amount;
	private String errorMessage;
}
