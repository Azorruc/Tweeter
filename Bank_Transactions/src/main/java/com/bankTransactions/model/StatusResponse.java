package com.bankTransactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusResponse {
	
	private long reference;
	
	private String status;
	
	private Float amount;
	
	private Float fee;

}
