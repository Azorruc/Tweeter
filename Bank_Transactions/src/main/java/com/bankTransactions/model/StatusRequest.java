package com.bankTransactions.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StatusRequest {
	
	private long reference;
	
	private String channel;	

}
