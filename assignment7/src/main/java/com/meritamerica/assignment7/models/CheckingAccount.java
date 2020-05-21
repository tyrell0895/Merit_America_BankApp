package com.meritamerica.assignment7.models;

import javax.persistence.Entity;

import com.meritamerica.assignment7.models.BankAccount;
@Entity
public class CheckingAccount extends BankAccount {
	
private long accountholder;
	


	static final double DEFAULT_INTEREST_RATE = .0001;
	
	
	public CheckingAccount() {
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