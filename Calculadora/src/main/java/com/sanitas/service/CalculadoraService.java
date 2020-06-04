package com.sanitas.service;

import java.math.BigDecimal;

import com.sanitas.dto.ResultDto;

public interface CalculadoraService {

	ResultDto calcular(BigDecimal operador1, BigDecimal operador2, String operacion);
}
