package com.gorest.controller;

import com.gorest.GeradorEmail;
import com.gorest.dominio.Usuario;
import com.gorest.validacao.GenericValidation;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UsuarioController {

    public static final String ENDPOIT_CRIAR_USUARIO = "/users";
    public static final String ENDPOIT_BUSCAR_TODOS_USUARIOS = "/users";
    public static final String ENDPOIT_BUSCAR_USUARIO_POR_ID = "/users/{id}";

    public static final String ENDPOINT_ATUALIZAR_USUARIO = "/users/{id}";

    public static final String ENDPOINT_DELETE_USUARIO = "/users/{id}";
    private static final String BEARER_TOKEN = "Bearer b7bdc0485c1046c5906b92d052bd56f906a9e3120ccfc9c80682958325668fb3";


    public Usuario[] deveListarTodosUsuarios() {
        String token = BEARER_TOKEN;
        Response response = given().headers("Authorization", token).when().get(ENDPOIT_BUSCAR_TODOS_USUARIOS);
        GenericValidation.validateResponse(response, HttpStatus.SC_OK);
        Usuario[] usuarios = response.getBody().as(Usuario[].class);
        return usuarios;
    }

    public void deveCriarUmUsuarioComSucesso(Usuario usuario) {
        String token = BEARER_TOKEN;
        Response response = given().headers("Authorization", token)
                .body(usuario).when().post(ENDPOIT_CRIAR_USUARIO);
        GenericValidation.validateResponse(response, HttpStatus.SC_CREATED, "name", "carol");

    }

    public Usuario deveBuscarUsuarioPorId(String id) {
        String token = BEARER_TOKEN;
        // Obter o ID esperado da lista de usuários
        String idEsperado = given().headers("Authorization", token).pathParam("id", id)
                .when().get(ENDPOIT_BUSCAR_TODOS_USUARIOS)
                .then().statusCode(200)
                .extract().jsonPath().getString("id[0]");
        // Imprimir o ID esperado
        System.out.println(idEsperado);
        // Buscar o usuário por ID
        Response response = given().headers("Authorization", token).pathParam("id", idEsperado)
                .when().get(ENDPOIT_BUSCAR_USUARIO_POR_ID);
        GenericValidation.validateResponse(response, HttpStatus.SC_OK);

        Usuario usuario = response.getBody().as(Usuario.class);
        return usuario;
    }


    public void deveAtualizarUsuario(Usuario usuario, String id) {
        String token = BEARER_TOKEN;
        // Obter o ID esperado da lista de usuários
        String idEsperado = given().headers("Authorization", token).pathParam("id", id)
                .when().get(ENDPOIT_BUSCAR_TODOS_USUARIOS)
                .then().statusCode(200)
                .extract().jsonPath().getString("id[0]");

        // Imprimir o ID esperado
        System.out.println(idEsperado);

        //atualizar usuario
        Response response = given().headers("Authorization", token).pathParam("id", idEsperado)
                .body(usuario)
                .when().put(ENDPOINT_ATUALIZAR_USUARIO);
        GenericValidation.validateResponse(response, HttpStatus.SC_OK, "name", "Joao");

    }

    public void deveExcluirUsuario(String id) {
        String token = BEARER_TOKEN;
        // Obter o ID esperado da lista de usuários
        String idEsperado = given().headers("Authorization", token).pathParam("id", id)
                .when().get(ENDPOIT_BUSCAR_TODOS_USUARIOS)
                .then().statusCode(200)
                .extract().jsonPath().getString("id[0]");

        // Imprimir o ID esperado
        System.out.println(idEsperado);


        //deleta usuario
        Response response = given().headers("Authorization", token).pathParam("id", idEsperado)
                .when().delete(ENDPOINT_DELETE_USUARIO);
        GenericValidation.validateResponse(response, HttpStatus.SC_NO_CONTENT);


    }


//    private String deveBuscarUsuarioPorId(String token) {
//
//    }
//    obter um elemento aleatório de um array
//    public static String obterElementoAleatorio(String[] array) {
//        Random random = new Random();
//        int indiceAleatorio = random.nextInt(array.length);
//        return array[indiceAleatorio];
//    }
//    private static String deveRetornarIdEsperado(String bearerToken, Usuario usuario) {
//        String idEsperado = given()
//                .headers("Authorization", "Bearer " + bearerToken)
//                .body(usuario).when().post(ENDPOIT_CRIAR_USUARIO)
//                .then().statusCode(equalTo(201))
//                .extract().jsonPath().getString("id");
//        System.out.println(idEsperado);
//        return idEsperado;
//
//    }
}

