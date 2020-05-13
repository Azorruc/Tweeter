package com.bankTransactions.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bankTransactions.model.StatusRequest;
import com.bankTransactions.model.StatusResponse;
import com.bankTransactions.model.Transaction;
import com.bankTransactions.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	@PostMapping("")
	@ResponseBody
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction){
		int result = transactionService.saveTransaction(transaction);
		
		if(result == 0) {
			return new ResponseEntity<>(
					"Transaction saved", 
					 HttpStatus.OK);
		}
		else {
			if(result == -1) {
				return new ResponseEntity<>(
						"Account balanca cannot be negative", 
						HttpStatus.BAD_REQUEST);
			}
			if(result == -2) {
				return new ResponseEntity<>(
						"IBAN doesn't comply with IBAN pattern", 
						HttpStatus.BAD_REQUEST);
			}
			//This code should be unreachable, but we return a HttpStatus 500 if saveTransaction Method return other value.
			return new ResponseEntity<>(
					"Error saving Transaction", 
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("")
	@ResponseBody
	public List<Transaction> getTransactions(@RequestParam String account, @RequestParam boolean asc){
		
		return transactionService.findTransaction(account, asc);
	}
	
	@PostMapping("/getStatus")
	@ResponseBody
	public StatusResponse getTransactionStatus(@RequestBody StatusRequest request) {
		
		return transactionService.getStatus(request);
	}
}
