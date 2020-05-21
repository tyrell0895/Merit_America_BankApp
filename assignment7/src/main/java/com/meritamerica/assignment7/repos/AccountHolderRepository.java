package com.meritamerica.assignment7.repos;



import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {

	
	AccountHolder findById(long id);
}
