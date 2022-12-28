package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Wallet {

	@Id
	@GeneratedValue
	private int walletId;
	private String mode;
	private Double amount;
	@JsonIgnore
	@OneToOne
	private User user;
}
