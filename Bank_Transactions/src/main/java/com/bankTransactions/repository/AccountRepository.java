package com.bankTransactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankTransactions.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {

}
