package com.sanitas.service;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sanitas.model.Operacion;


@Service
public class CalculadoraService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CalculadoraService.class);
	/**
     * Intentamos parsear la operación a las operaciones permitidas, si no se puede lanzamos un error
     * si la operación es correcta, realizamos la operación y devolvemos el resultado.
     * @param operador1
     * @param operador2
     * @param operacionTxt
     * @return
     */
	public double calcular(BigDecimal operador1, BigDecimal operador2, String operacionTxt) {
		
		if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Calculando resultado para : {} {} {}", operador1, operador2, operacionTxt);
        }

		Operacion operacion = Operacion.parsear(operacionTxt);

        if(operacion == null) {
            throw new RuntimeException("Operación imposible de procesar: " + operacionTxt);
        }
        
        switch(operacion) {
			case RESTA:
				
				return operador1.subtract(operador2).doubleValue();
			case SUMA:
				
				return operador1.add(operador2).doubleValue();
			default:
				if(LOGGER.isErrorEnabled()) {
                    LOGGER.error("Operación no soportada para ser calculada: {}", operacion);
                }
                throw new RuntimeException("Operación no soportada para ser calculada: " + operacion.toString());
        
        }
	}

}
