package com.meritamerica.assignment6.controller; 

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment7.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment7.exceptions.NotFoundException;
import com.meritamerica.assignment7.models.*;
import com.meritamerica.assignment7.repos.*;

@RestController
public class MeritBankController {
	List<String> strings = new ArrayList<String>(); 
	
	@Autowired
	private AccountHolderContactDetailsRepository accountHolderContactRepo;
	@Autowired
	private AccountHolderRepository accountHolderRepo;
	@Autowired
	private CDAccountRepository cdAccountRepo;
	@Autowired
	private CDOfferingRepository cdOfferingRepo;
	@Autowired
	private CheckingAccountRepository checkingAccountRepo;
	@Autowired
	private SavingsAccountRepository savingsAccountRepo;
	
	
	//List<AccountHolder> ac = new ArrayList<AccountHolder>(); 
	
	//List<CheckingAccount> ca = new ArrayList<CheckingAccount>(); 

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String test() {
		return "Welcome to MeritBank"; 
	}
	
	@GetMapping(value="/strings") //@RequestMapping(value = "/strings", method = RequestMethod.GET)
	public List<String> getStrings() {
		return strings; 
	}
	
	@PostMapping(value = "/strings")
	//http://localhost:8080/strings POST, insert, then GET, you should see all inputs from prior
	public String addString(@RequestBody String string) {
		//String string = "test"; 
		strings.add(string); 
		return string; 
		
	} 
	
	@GetMapping(value = "/AccountHolders")

	public List<AccountHolder> getAccountHolders(){

		return accountHolderRepo.findAll();

	}
	
	@PostMapping(value = "/AccountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		accountHolderRepo.save(accountHolder);
		return accountHolder; 
	}
	
	@PostMapping(value = "/ContactDetails")
	@ResponseStatus(HttpStatus.CREATED)
	public void addContactDetails(@RequestBody @Valid AccountHolderContactDetails contactDetails) {
		accountHolderContactRepo.save(contactDetails);
	}
	
	@GetMapping(value = "/ContactDetails")
	public List<AccountHolderContactDetails> getContactDetails(){
		return accountHolderContactRepo.findAll();
	}
	
	//Check URL Mapping
	
	@GetMapping(value = "/AccountHolders/{id}")
	public AccountHolder getACById(@PathVariable (name = "id" )long id)  throws NotFoundException {
			//if (id > ac.size() -1) {
				//throw new NotFoundException ("invalid id"); 
		return accountHolderRepo.findById(id); 
		}
	
	@PostMapping(value ="/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAccount(@RequestBody @Valid CheckingAccount checkingAccount, @PathVariable
			(name = "id") long id) throws ExceedsCombinedBalanceLimitException, NotFoundException{
		AccountHolder a = accountHolderRepo.findById(id); 
		a.addCheckingAccount(checkingAccount);
		checkingAccountRepo.save(checkingAccount); 
		return checkingAccount; 
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CheckingAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CheckingAccount> getCheckingAccount(@PathVariable (name = "id") long id) throws NotFoundException {
		return checkingAccountRepo.findByAccountholder(id);
		
		
		//AccountHolder a = accountHolderRepo.findById(id); 
		//return a.getCheckingAccounts(); 
	}
	@PostMapping(value ="/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAccount(@RequestBody @Valid SavingsAccount savingsAccount, @PathVariable
			(name = "id") long id) throws ExceedsCombinedBalanceLimitException, NotFoundException{
		AccountHolder a = accountHolderRepo.findById(id);
		a.addSavingsAccount(savingsAccount); 
		savingsAccountRepo.save(savingsAccount);
		return savingsAccount; 
	}
	
	@GetMapping(value = "/AccountHolders/{id}/SavingsAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<SavingsAccount> getSavingsAccount(@PathVariable (name = "id") long id) throws NotFoundException {
		return savingsAccountRepo.findByAccountholder(id);
		
		
		//AccountHolder a = accountHolderRepo.findById(id); 
		//return a.getSavingsAccounts(); 
	}
	
	@PostMapping(value ="/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@RequestBody @Valid CDAccount cdAccount, @PathVariable
			(name = "id") long id) throws ExceedsCombinedBalanceLimitException, NotFoundException{
		AccountHolder a = accountHolderRepo.findById(id); 
		a.addCDAccount(cdAccount);
		cdAccountRepo.save(cdAccount);
		
		return cdAccount; 
	}
	
	@GetMapping(value = "/AccountHolders/{id}/CDAccounts")
	@ResponseStatus(HttpStatus.OK)
	public List<CDAccount> getCDAccount(@PathVariable (name = "id") long id) throws NotFoundException {
		return cdAccountRepo.findByAccountholder(id);
		
		//AccountHolder a = accountHolderRepo.findById(id); 
		
		//return a.getCDAccounts(); 
	}
	
	@PostMapping(value ="/CDOffering")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering addCDOffering(@RequestBody @Valid CDOffering cdOffering) {
		cdOfferingRepo.save(cdOffering); 
		return cdOffering; 
	}
	
	@GetMapping(value = "/CDOffering")
	@ResponseStatus(HttpStatus.OK)
	public List<CDOffering> getCDOffering() throws NotFoundException {
		return cdOfferingRepo.findAll(); 
	}
	
		

}
