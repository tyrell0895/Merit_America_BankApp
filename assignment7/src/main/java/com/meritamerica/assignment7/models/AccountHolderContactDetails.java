package com.meritamerica.assignment7.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "contact_details", catalog = "bankDatabase")
public class AccountHolderContactDetails {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "contactId")
	private Long contactId;
	
	
	private String emailAddress;
	
	private String phoneNumber;

	private String address;
	
	
	public AccountHolderContactDetails() {}
	
	public String getAddress() {
		return address;
	}
	public AccountHolderContactDetails setAddress(String address) {
		this.address = address;
		return this;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public AccountHolderContactDetails setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
		return this;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public AccountHolderContactDetails setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
		return this;
	}

}
