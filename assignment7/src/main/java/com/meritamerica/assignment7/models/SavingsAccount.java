package com.meritamerica.assignment7.models;

import javax.persistence.Entity;

import com.meritamerica.assignment7.models.BankAccount;
@Entity
public class SavingsAccount extends BankAccount {
	static final double DEFAULT_INTEREST_RATE = .01;
	
	private long accountholder;
	

	
	public SavingsAccount() { 
		super();
		super.setInterestRate(DEFAULT_INTEREST_RATE);
	}
	public long getAccountholder() {
		return accountholder;
	}




	public void setAccountholder(Long accountholder) {
		this.accountholder = accountholder;
	
	}

}
