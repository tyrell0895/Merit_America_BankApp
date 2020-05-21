package com.meritamerica.assignment7.models;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
/*
 * import javax.validation.constraints- NotNull; NotBlank; 
 * Dont need these, set in MeritBank
 */

@Entity
public class CDAccount extends BankAccount {
	
	@Min(1)
	private int term;
	
	private long accountholder;
	
	
	private long cdoffering;
	

	public CDAccount() { super(); }
	
	
	@Override
	public boolean withdraw(double amount) {
		return false;
	}
	
	@Override
	public boolean deposit(double amount) {
		return false;
	}
	
	
	
	public int getTerm() { return this.term; }
	public void setTerm(int n) { this.term = n; }


	


	
	public long getAccountholder() {
		return accountholder;
	}




	public void setAccountholder(Long accountholder) {
		this.accountholder = accountholder;
	
	}

	public long getCdoffering() {
		return cdoffering;
	}


	public void setCdoffering(long cdoffering) {
		this.cdoffering = cdoffering;
	}

}

