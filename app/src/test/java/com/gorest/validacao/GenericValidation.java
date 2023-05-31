package com.gorest.validacao;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class GenericValidation {

    public static void validateResponse(Response response, int statusCode) {
        response.then().statusCode(statusCode);
    }

    public static void validateResponse(Response response, int statusCode, String key, String value) {
        response.then()
                .statusCode(statusCode)
                .body(key, equalTo(value));
    }
}
