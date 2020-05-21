package com.meritamerica.assignment7.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
@Entity
@Table(name = "cd_offering")
public class CDOffering {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	
	@Min(1)
	private int term;
	
	
	@DecimalMin("0.0")
	@DecimalMax("0.99999")
	private double interestRate;
	
	@OneToMany(cascade = CascadeType.ALL)
	List<CDAccount> cdAccounts;
	
	
	public CDOffering(int term, double interestRate){
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public CDOffering() {
		cdAccounts = new ArrayList<CDAccount>();
	}
	
	public int getTerm() { return this.term; }
	public void setTerm(int n) { this.term = n; }
	
	public double getInterestRate() { return this.interestRate; }
	public void setInterestRate(double n) { this.interestRate = n; }


}
