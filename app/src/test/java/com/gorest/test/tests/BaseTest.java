package com.gorest.test.tests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
public class BaseTest {
    @BeforeClass
    public static void setupRestAssured() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        baseURI = "https://gorest.co.in";
        basePath = "/public/v2";
        RestAssured.requestSpecification = new RequestSpecBuilder().
                setContentType(ContentType.JSON).
                build();

        RestAssured.responseSpecification = new ResponseSpecBuilder().
                expectContentType(ContentType.JSON).
                build();
    }
}
