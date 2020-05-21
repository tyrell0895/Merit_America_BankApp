package com.meritamerica.assignment7.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment7.models.AccountHolderContactDetails;

public interface AccountHolderContactDetailsRepository extends JpaRepository<AccountHolderContactDetails, Long> {

	AccountHolderContactDetails findById(long id);
}
