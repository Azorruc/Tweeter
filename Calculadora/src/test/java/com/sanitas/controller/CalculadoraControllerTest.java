package com.sanitas.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.net.URI;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.sanitas.dto.ResultDto;
import com.sanitas.enumeration.Operacion;
import com.sanitas.service.CalculadoraService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculadoraControllerTest {

	private MockMvc mvc;
	
	@LocalServerPort
    int randomServerPort;
	
	@Mock
	CalculadoraService calculadoraService;
	
	@InjectMocks
	private CalculadoraController calculadoraController;
	
	private static final String URI_BASE = "/api/calculadora/";
	private static final String CALCULA = "calcula/";
	private static final String SUMA_URI = URI_BASE.concat(CALCULA).concat(Operacion.SUMA.getSigno());
	private static final String RESTA_URI = URI_BASE.concat(CALCULA).concat(Operacion.RESTA.getSigno());
	private static final String FIRST_PARAM_NAME = "operador1";
	private static final String SECOND_PARAM_NAME = "operador2";
	private static final String FIRST_PARAM_VALID_VALUE = "2.3";
	private static final String SECOND_PARAM_VALID_VALUE = "1.5";
	private static final String PARAM_INVALID_VALUE = "X";
	
	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(calculadoraController).build();
	}
	
	@Test
	public void noRequiredParams() throws Exception {
		mvc.perform(get(RESTA_URI)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void noRequiredFirstParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void noRequiredSecondParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidFirstParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void invalidSecondParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(SECOND_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void emptyFirstParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(PARAM_INVALID_VALUE))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void emptySecondParam() throws Exception {
		mvc.perform(get(RESTA_URI.concat("?").concat(SECOND_PARAM_NAME).concat("="))).andExpect(status().isBadRequest());
	}
	
	@Test
	public void calculateAddition_responseOK() throws Exception {
		/**
		 * Given
		 */

		RestTemplate restTemplate = new RestTemplate();
		 String baseUrl = "http://localhost:" + randomServerPort + SUMA_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE)
				.concat("&").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE);
		 URI uri = new URI(baseUrl);
		 
		 ResponseEntity<ResultDto> resultado = restTemplate.getForEntity(uri, ResultDto.class);
		 
		//Comprueba el resultado
	    Assert.assertEquals(200, resultado.getStatusCodeValue());
	    ResultDto result = (ResultDto) resultado.getBody();
	    Assert.assertEquals(result.isSuccess(), true);
	    Assert.assertEquals(result.getResult(), "3.8");
	}
	
	@Test
	public void calculateSubtraction_responseOK() throws Exception {
		/**
		 * Given
		 */
		ResultDto resultOperation = new ResultDto();
		resultOperation.setSuccess(true);
		resultOperation.setResult("0.8");
		RestTemplate restTemplate = new RestTemplate();
		 String baseUrl = "http://localhost:" + randomServerPort + RESTA_URI.concat("?").concat(FIRST_PARAM_NAME).concat("=").concat(FIRST_PARAM_VALID_VALUE)
				.concat("&").concat(SECOND_PARAM_NAME).concat("=").concat(SECOND_PARAM_VALID_VALUE);
		 URI uri = new URI(baseUrl);
		 
		 ResponseEntity<ResultDto> resultado = restTemplate.getForEntity(uri, ResultDto.class);
		 
		//Comprueba el resultado
	    Assert.assertEquals(200, resultado.getStatusCodeValue());
	    ResultDto result = (ResultDto) resultado.getBody();
	    Assert.assertEquals(result.isSuccess(), true);
	    Assert.assertEquals(result.getResult(), "0.8");
	}

}
