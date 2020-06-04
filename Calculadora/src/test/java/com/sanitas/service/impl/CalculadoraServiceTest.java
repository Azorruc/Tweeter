package com.sanitas.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sanitas.dto.ResultDto;
import com.sanitas.enumeration.Operacion;

import io.corp.calculator.TracerImpl;

class CalculadoraServiceTest {

	@Mock
	private TracerImpl trace;
	
	@InjectMocks
	private CalculadoraServiceImpl operationService;
	
	private static final String NULL_ERROR = "El valor null no esta permitido";
	private static final String OPERATOR_ERROR = "La operacion no esta permitida, las posibles operaciones son SUMA y RESTA";
	
	private static final String FIRST_PARAM_VALID_VALUE = "2.3";
	private static final String SECOND_PARAM_VALID_VALUE = "3.5";
	private static final String RESULT_SUMA = "5.8";
	private static final String RESULT_RESTA = "-1.2";
	@BeforeEach
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void calculateNullValues() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calcular(null, null, Operacion.SUMA.getSigno());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateFisrtValueIsNull() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calcular(null, new BigDecimal(SECOND_PARAM_VALID_VALUE), Operacion.SUMA.getSigno());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateSecondValueIsNull() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(NULL_ERROR);
		ResultDto result = operationService.calcular(new BigDecimal(FIRST_PARAM_VALID_VALUE), null, Operacion.SUMA.getSigno());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateAddition() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(true);
		expected.setResult(RESULT_SUMA);
		ResultDto result = operationService.calcular(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operacion.SUMA.getSigno());
		assertEquals(expected, result);
	}
	
	@Test
	public void calculateSubtraction() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(true);
		expected.setResult(RESULT_RESTA);
		ResultDto result = operationService.calcular(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), Operacion.RESTA.getSigno());
		assertEquals(expected, result);
	}
	
	@Test
	public void operationNotImplemented() {
		ResultDto expected = new ResultDto();
		expected.setSuccess(false);
		expected.setResult(OPERATOR_ERROR);
		ResultDto result = operationService.calcular(new BigDecimal(FIRST_PARAM_VALID_VALUE),
				new BigDecimal(SECOND_PARAM_VALID_VALUE), "CODE");
		assertEquals(expected, result);
	}

}
