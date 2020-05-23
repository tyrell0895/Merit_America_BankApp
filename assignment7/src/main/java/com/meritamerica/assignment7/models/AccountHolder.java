package com.meritamerica.assignment7.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;

@Entity 
@Table (name = "accountholders", catalog = "bankdatabase")
public class AccountHolder implements Comparable <AccountHolder> {
	
	@NotBlank(message = "First Name is required")
	private String firstName;
	
	private String middleName;
	
	@NotBlank(message = "Last Name is required")
	private String lastName;
	
	@Size(min=9, max=11)
	@NotBlank(message = "SSN is required")
	private String ssn;
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_Id")
	private Long id;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contactId", referencedColumnName = "contactId")
	private AccountHolderContactDetails accountHolderContactDetails;
	

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="account_Id", referencedColumnName = "account_Id")
	private List<CheckingAccount> checkingAccounts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="account_Id", referencedColumnName = "account_Id")
	private List<SavingsAccount> savingsAccounts;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name ="account_Id", referencedColumnName = "account_Id")
	private List<CDAccount> cdAccounts;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name ="users_id",referencedColumnName = "users_id")
	private Users users;
	

	public Users getUsers() {
		return users;
	}

	public void setUser(Users users) {
		this.users = users;
	}

	private int NumberOfCheckingAccounts ;
	private int NumberOfSavingsAccounts ;
	private int NumberOfCDAccounts ;
	
	
	//new account holder
	public AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.checkingAccounts = new ArrayList <>();
		this.savingsAccounts = new ArrayList <>();
		this.cdAccounts = new ArrayList <>();
	 
	}
	
	public AccountHolder() {
		this.checkingAccounts = new ArrayList <>();
		this.savingsAccounts = new ArrayList <>();
		this.cdAccounts = new ArrayList <>();
		this.accountHolderContactDetails = new AccountHolderContactDetails();
	}
	
	
	
	public boolean addCheckingAccount(CheckingAccount b) throws ExceedsCombinedBalanceLimitException {
		if(b == null) { return false; }
		if(getCheckingBalance() + getSavingsBalance() + b.getBalance() >= 250000 ) {
			System.out.println(b.getBalance());
			System.out.println(getCheckingBalance() + getSavingsBalance() + b.getBalance());
			throw new ExceedsCombinedBalanceLimitException();
		}
		checkingAccounts.add(b);
		return true;
	}
	
	public boolean addSavingsAccount(SavingsAccount b) throws ExceedsCombinedBalanceLimitException {
		if(b == null) { return false; }
		if(getCheckingBalance() + getSavingsBalance() + b.getBalance() >= 250000 ) {
			throw new ExceedsCombinedBalanceLimitException();
		}
		savingsAccounts.add(b);
		return true;
	}
	
	public boolean addCDAccount(CDAccount b) {
		if(b == null) { return false; }
		cdAccounts.add(b);
		return true;
	}
	
	
	
	
	public double getCheckingBalance() {
		double sum = 0;
		for(BankAccount b : checkingAccounts) {
			sum += b.getBalance();
		}
		return sum;
	}
	public double getSavingsBalance() {
		double sum = 0;
		for(BankAccount b : savingsAccounts) {
			sum += b.getBalance();
		}
		return sum;
	}
	public double getCDBalance() {
		double sum = 0;
		for(BankAccount b : cdAccounts) {
			sum += b.getBalance();
		}
		return sum;
	}
	public double getCombinedBalance() {
		double sum = 0;
		sum += getCheckingBalance();
		sum += getSavingsBalance();
		sum += getCDBalance();
		return sum;
	}
	
	
	
	public String getFirstName() { return firstName; }
	public AccountHolder setFirstName(String s) { this.firstName = s; return this; }

	public String getMiddleName() { return middleName; }
	public AccountHolder setMiddleName(String s) { this.middleName = s; return this;}

	public String getLastName() { return lastName; }
	public AccountHolder setLastName(String s) { this.lastName = s; return this; }

	

	public List<CheckingAccount> getCheckingAccounts() { return checkingAccounts; }

	public List<SavingsAccount> getSavingsAccounts() { return savingsAccounts; }

	public List<CDAccount> getCDAccounts() { return cdAccounts; }
	

	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {this.id = id;}

	public AccountHolderContactDetails getAccountHolderContactDetails() {
		return accountHolderContactDetails;
	}

	public void setAccountHolderContactDetails(AccountHolderContactDetails accountHolderContactDetails) {
		this.accountHolderContactDetails = accountHolderContactDetails;
	}

	public List<CDAccount> getCdAccounts() {return cdAccounts;}

	public void setCdAccounts(List<CDAccount> cdAccounts) {this.cdAccounts = cdAccounts;}

	public void setCheckingAccounts(List<CheckingAccount> checkingAccounts) {this.checkingAccounts = checkingAccounts;}

	public void setSavingsAccounts(List<SavingsAccount> savingsAccounts) {this.savingsAccounts = savingsAccounts;}

	
	@Override
	public int compareTo(AccountHolder other) {
		int mySum = (int) getCombinedBalance();
		int otherSum = (int) other.getCombinedBalance();
		return mySum - otherSum;
	}

	public int getNumberOfCDAccounts() {
		return NumberOfCDAccounts;
	}

	public AccountHolder setNumberOfCDAccounts(int numberOfCDAccounts) {
		NumberOfCDAccounts = numberOfCDAccounts;
		return this;
	}

	public int getNumberOfSavingsAccounts() {
		return NumberOfSavingsAccounts;
	}

	public AccountHolder setNumberOfSavingsAccounts(int numberOfSavingsAccounts) {
		NumberOfSavingsAccounts = numberOfSavingsAccounts;
		return this;
	}

	public int getNumberOfCheckingAccounts() {
		return NumberOfCheckingAccounts;
	}

	public AccountHolder setNumberOfCheckingAccounts(int numberOfCheckingAccounts) {
		NumberOfCheckingAccounts = numberOfCheckingAccounts;
		return this;
	}
	
	
	
	
	
	
	
	
	
}


