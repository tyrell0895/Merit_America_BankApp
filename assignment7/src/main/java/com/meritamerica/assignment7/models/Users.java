package com.meritamerica.assignment7.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Users", catalog ="bankDatabase")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;
	private String username; 
	private String password; 
	private boolean isActive;
	private String roles;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name="accountholders",referencedColumnName = "account_Id")
	AccountHolder accountHolder;
	//in postman add a field for accountholder which will be the same as the account id 
	// 
	public Users() {
		username = "";
		password = "";
		isActive = true;
		roles = "";
	}
	
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the userName
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUsername(String userName) {
		this.username = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * @return the roles
	 */
	public String getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	} 
	public AccountHolder getAccount() {
		return accountHolder;
	}
	public void setAccount(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	
	

}
