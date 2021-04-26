package com.tweeter.model;

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
public class Tweet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "usuario", nullable = false)
	@IBAN
	@NotEmpty(message="usuario es obligatorio")
	private String usuario;
	
	@Column(name = "texto", nullable = true)
	private String texto;
	
	@NotNull(message="localizacion obligatoria")
	@Column(name = "loc", nullable = false)
	private String loc;
	
	@Column(name = "val", nullable = true)
	private boolean val;
	
	@Column(name = "hastag", nullable = true)
	private String hastag;
	
	

}
