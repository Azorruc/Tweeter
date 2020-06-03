package com.sanitas.model;


import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Operacion {
	SUMA("+"),
    RESTA("-");
	
	//TODO añadir más operaciones
	
	//La operación podrá ser representada o bién por el literal o bién por el signo matemático
	public String getSigno() {
		return signo;
	}

	private static final Operacion[] valores = new Operacion[]{SUMA, RESTA};
	
	private String signo;
	
	@JsonCreator
    public static Operacion parsear(String valor) {

        for (int i = 0; i < valores.length; ++i) {
            Operacion opActual = valores[i];
            if (valor.equalsIgnoreCase(opActual.name()) ||
                    valor.equalsIgnoreCase(opActual.getSigno())) {
                return opActual;
            }
        }

        throw new RuntimeException("Operación no soportada para el valor: " + valor);
    }
}
