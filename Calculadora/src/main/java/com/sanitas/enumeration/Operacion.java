package com.sanitas.enumeration;


import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum Operacion {
	SUMA("+"),
    RESTA("-");
	
	//OPTIONAL se podrían añadir más operaciones como multiplicación y división
	
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
        
        //Si no encontramos el valor entre las operaciones definidas, devolvemos null
        return null;
    }
}
