package com.bankTransactions.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankTransactions.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	public List<Transaction> findByIbanOrderByAmountAsc(String account_iban);
	
	public List<Transaction> findByIbanOrderByAmountDesc(String account_iban);
}
