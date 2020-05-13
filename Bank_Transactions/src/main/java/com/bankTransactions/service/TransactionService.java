package com.bankTransactions.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bankTransactions.model.Account;
import com.bankTransactions.model.Transaction;
import com.bankTransactions.model.StatusRequest;
import com.bankTransactions.model.StatusResponse;
import com.bankTransactions.repository.AccountRepository;
import com.bankTransactions.repository.TransactionRepository;

@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	public int saveTransaction(Transaction transaction) {
		
		String accountIban = transaction.getIban();
		
		float totalAmount;
		float balance;
		
		if(transaction.getFee() != null) {
			totalAmount = transaction.getAmount() - transaction.getFee();
		}
		else {
			totalAmount = transaction.getAmount();
		}
		Account account;
		try {
			account = accountRepository.findById(accountIban).get();
		}
		catch(NoSuchElementException e) {
			account = null;
		}
		
		if(account == null) {
			balance = totalAmount;
			
			if(balance<0) {
				//The balance of the account would be negative, so we wont update account balance
				//neither insert transaction
				return -1;
			}
			else {
				account = new Account(accountIban,balance);
			}
		}
		else {
			balance = account.getBalance() + totalAmount;
			
			if(balance<0) {
				//The balance of the account would be negative, so we wont update account balance
				//neither insert transaction
				return -1;
			}
			else {
				account.setBalance(balance);
			}	
		}
		
		try{
			transactionRepository.save(transaction);
		}
		catch(ConstraintViolationException e) {
			return -2;
		}
		
		accountRepository.save(account);
		
		return 0;		

	}
	
	public List<Transaction> findTransaction(String account, boolean ascending) {
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		if(ascending){
			transactions = transactionRepository.findByIbanOrderByAmountAsc(account);
		}
		else {
			transactions = transactionRepository.findByIbanOrderByAmountDesc(account);
		}
		
		return transactions;
	}
	
	public StatusResponse getStatus(StatusRequest request) {
		
		StatusResponse response = new StatusResponse();
		
		Transaction transaction = transactionRepository.findById(request.getReference()).get();
		
		response.setReference(request.getReference());
		
		if(transaction == null) {
			
			response.setStatus("INVALID");
			
		}
		else {
			if(request.getChannel().equals("CLIENT") || request.getChannel().equals("ATM")) {
				if(transaction.getDate().isBefore(LocalDate.now())){
					response.setStatus("SETTLED");
				}
				if(transaction.getDate().isEqual(LocalDate.now())) {
					response.setStatus("PENDING");
				}
				if(transaction.getDate().isAfter(LocalDate.now())) {
					if(request.getChannel().equals("CLIENT")) {
						response.setStatus("FUTURE");
					}
					else {
						response.setStatus("PENDING");
					}
				}
				response.setAmount(transaction.getAmount()-transaction.getFee());
			}
			else {
				if(request.getChannel().equals("INTERNAL")) {
					if(transaction.getDate().isBefore(LocalDate.now())){
						response.setStatus("SETTLED");
					}
					if(transaction.getDate().isEqual(LocalDate.now())) {
						response.setStatus("PENDING");
					}
					if(transaction.getDate().isAfter(LocalDate.now())) {
						response.setStatus("FUTURE");
					}
					response.setAmount(transaction.getAmount());
					response.setFee(transaction.getFee());
				}
			}
		}
		
		return response;
		
	}
}
