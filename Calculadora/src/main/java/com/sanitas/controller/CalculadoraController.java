package com.sanitas.controller;


import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanitas.dto.ResultDto;
import com.sanitas.service.impl.CalculadoraServiceImpl;

@RestController
@RequestMapping("/api/calculadora")
public class CalculadoraController {
	
	@Autowired
	private CalculadoraServiceImpl calculadoraService;
	

	
	@GetMapping(value = "/calcula/{operacion}")
    public ResponseEntity<ResultDto> calcula(@RequestParam(name = "operador1") BigDecimal operador1,
                                            @RequestParam(name = "operador2") BigDecimal operador2,
                                            @PathVariable(name = "operacion") String operacion) {
		
		final ResultDto response = calculadoraService.calcular(operador1, operador2, operacion);
		
		if (response != null) {
			if(response.isSuccess()) {
				return ResponseEntity.ok(response);
			}
			return ResponseEntity.badRequest().body(response);
		}
		return ResponseEntity.noContent().build();
        
    }

}
