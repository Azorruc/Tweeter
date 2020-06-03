package com.sanitas;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConfigAplicacion.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculadoraApplicationTests {

	@LocalServerPort
    int randomServerPort;

    /**
     * Invoca una llamada GET localhost:(puerto_aleatorio)/api/calcula con los parametros de la funcion
     * @param operador1
     * @param operador2
     * @param operacion
     * @return
     * @throws URISyntaxException
     */
    private ResponseEntity<String> calcula(String operador1, String operador2, String operacion) throws URISyntaxException {
        RestTemplate restTemplate = new RestTemplate();

        final String baseUrl = "http://localhost:" + randomServerPort +
                "/api/calcula?operador1=" + operador1 +
                "&operador2=" + operador2 + "&operacion=" + operacion;
        URI uri = new URI(baseUrl);

        ResponseEntity<String> resultado = restTemplate.getForEntity(uri, String.class);
        return resultado;
    }

    @Test
    public void testSumaConÉxito() throws URISyntaxException {

        ResponseEntity<String> resultado = calcula(String.valueOf(3.5), String.valueOf(7.35), "suma");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals("10.85", resultado.getBody().toString());
    }
    
    @Test
    public void testSumaConÉxitoSigno() throws URISyntaxException {

        ResponseEntity<String> resultado = calcula(String.valueOf(3.5), String.valueOf(7.35), "%2B");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals("10.85", resultado.getBody().toString());
    }
    
    @Test
    public void testRestaConÉxito() throws URISyntaxException {

        ResponseEntity<String> resultado = calcula(String.valueOf(2.5), String.valueOf(3.0), "resta");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals("-0.5", resultado.getBody().toString());
    }
    
    @Test
    public void testRestaConÉxitoSigno() throws URISyntaxException {

        ResponseEntity<String> resultado = calcula(String.valueOf(2.5), String.valueOf(3.0), "-");

        //Comprueba el resultado
        Assert.assertEquals(200, resultado.getStatusCodeValue());
        Assert.assertEquals("-0.5", resultado.getBody().toString());
    }
}
