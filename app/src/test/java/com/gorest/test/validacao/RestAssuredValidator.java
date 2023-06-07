package com.gorest.test.validacao;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
public class RestAssuredValidator {

    private Response response;
    private JsonPath jsonPath;

    public RestAssuredValidator(Response response) {
        this.response = response;
        this.jsonPath = response.jsonPath();
    }

    public RestAssuredValidator verificarStatusCode(int statusCodeEsperado) {
        int statusCodeAtual = response.getStatusCode();
        Assertions.assertEquals(statusCodeEsperado, statusCodeAtual);
        return this;
    }

    public RestAssuredValidator verificarCampo(String campo, Object valorEsperado) {
        Object valorAtual = jsonPath.get(campo);
        Assertions.assertEquals(valorEsperado, valorAtual);
        return this;
    }

    public RestAssuredValidator verificarCampoExistente(String campo) {
        Object valorAtual = jsonPath.get(campo);
        Assertions.assertNotNull(valorAtual);
        return this;
    }


}