package com.sanitas.controler;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanitas.service.CalculadoraService;

import io.corp.calculator.TracerImpl;

@RestController
@RequestMapping("/api")
public class CalculadoraController {
	
	@Autowired
	private CalculadoraService calculadoraService;
	
	private TracerImpl tracer = new TracerImpl();
	
	@GetMapping(value = "/calcula")
    public ResponseEntity<String> calcula(@RequestParam(name = "operador1") String operador1,
                                            @RequestParam(name = "operador2") String operador2,
                                            @RequestParam(name = "operacion") String operacion) {
		BigDecimal op1;
		BigDecimal op2;
		
		try {
			op1 = new BigDecimal(operador1);
		}
		catch(NumberFormatException e) {
			return new ResponseEntity<>("El operador1 debe ser un número",HttpStatus.BAD_REQUEST);
		}
		
		try {
			op2 = new BigDecimal(operador2);
		}
		catch(NumberFormatException e) {
			return new ResponseEntity<>("El operador2 debe ser un número",HttpStatus.BAD_REQUEST);
		}
		
        double result = calculadoraService.calcular(op1, op2, operacion);
        
        tracer.trace(result);
        
        return new ResponseEntity<>(String.valueOf(result), HttpStatus.OK);
    }

}
