package com.sanitas.service.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sanitas.dto.ResultDto;
import com.sanitas.enumeration.Operacion;
import com.sanitas.service.CalculadoraService;

import io.corp.calculator.TracerImpl;


@Service
public class CalculadoraServiceImpl implements CalculadoraService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraServiceImpl.class);
	
	private static final String NULL_ERROR = "El valor null no esta permitido";
	private static final String OPERATOR_ERROR = "La operacion no esta permitida, las posibles operaciones son SUMA y RESTA";
	
	private TracerImpl tracer = new TracerImpl();
	
	/**
     * Intentamos parsear la operación a las operaciones permitidas, si no se puede lanzamos un error
     * si la operación es correcta, realizamos la operación y devolvemos el resultado.
     * @param operador1
     * @param operador2
     * @param operacionTxt
     * @return
     */
	@Override
	public ResultDto calcular(BigDecimal operador1, BigDecimal operador2, String operacionTxt) {
		
		if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calculando resultado para : {} {} {}", operador1, operador2, operacionTxt);
        }
		
		if (operador1 == null || operador2 == null) {
			return new ResultDto(NULL_ERROR, false);
		}

		Operacion operacion = Operacion.parsear(operacionTxt);

        if(operacion == null) {
            return new ResultDto(OPERATOR_ERROR,false);
        }
        
        BigDecimal result;
        
        switch(operacion) {
			case RESTA:
				result = operador1.subtract(operador2);
				
				break;
				
			case SUMA:
				
				result = operador1.add(operador2);
				
				break;
			default:
				if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("Operación no soportada para ser calculada: {}", operacion);
                }
                
				return new ResultDto(OPERATOR_ERROR, false);
        
        }
        
        tracer.trace(result);
        return new ResultDto(result.toString(),true);
	}

}
