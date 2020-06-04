package com.sanitas.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultDto implements Serializable {

	private static final long serialVersionUID = 4513741196180970122L;
		
	private String result;
	private boolean success;
		
}
