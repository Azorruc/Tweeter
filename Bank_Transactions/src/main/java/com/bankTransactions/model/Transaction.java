package com.bankTransactions.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.bval.extras.constraints.checkdigit.IBAN;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long reference;
	
	@Column(name = "iban", nullable = false)
	@IBAN
	@NotEmpty(message="account IBAN code is mandatory")
	private String iban;
	
	@Column(name = "date", nullable = true)
	private LocalDate date;
	
	@NotNull(message="transaction amount is mandatory")
	@Column(name = "amount", nullable = false)
	private Float amount;
	
	@Column(name = "fee", nullable = true)
	private Float fee;
	
	@Column(name = "description", nullable = true)
	private String description;
	
	

}
