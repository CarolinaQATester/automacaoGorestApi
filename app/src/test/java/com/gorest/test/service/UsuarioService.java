package com.gorest.test.service;

import com.gorest.test.dominio.Usuario;
import com.gorest.test.utils.GeradorEmail;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    private static final String LISTAR_TODOS_USUARIOS_ENDPOINT = "/users";
     private static final String CRIAR_USUARIO_ENDPOINT = "/users";
    private static final String BUSCAR_USUARIO_POR_ID_ENDPOINT = "/users/{id}";
    private static final String ATUALIZA_TODO_O_USUARIO_ENDPOINT = "/users/{id}";
    private static final String ATUALIZA_UM_CAMPO_USUARIO_ENDPOINT = "/users/{id}";
    private static final String EXCLUIR_USUARIO_POR_ID_ENDPOINT =  "/users/{id}";
    private static final String ACESS_TOKEN = "b7bdc0485c1046c5906b92d052bd56f906a9e3120ccfc9c80682958325668fb3";

    public Response deveBucarTodosOsUsuarios() {
        Response response = given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .when()
                .get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().extract().response();
        System.out.println(response.asString());
        return response;
    }
    public Response deveBuscarUmUsuarioPorId() {
        int idEsperado =  given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .when().get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().statusCode(200).extract().path("id[0]");
        System.out.println(idEsperado);
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .pathParam("id", idEsperado)
                .when()
                .get(BUSCAR_USUARIO_POR_ID_ENDPOINT)
                .then().statusCode(200).extract().response();
        System.out.println(response.asString());
        return response;
    }

//    private int retornaidEsperado(int id) {
//       given()
//                .header("Authorization", "Bearer " + ACESS_TOKEN)
//                .when().get(LISTAR_TODOS_USUARIOS_ENDPOINT)
//                .then().statusCode(200).extract().path("id[0]");
//        System.out.println(id);
//        return id;
//    }

    public Response deveCriarUmUsuario(){
        Usuario usuario = new Usuario("carolina", "female", GeradorEmail.generateRandomEmail(), "active");
        Response response = given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .body(usuario)
                .when()
                .post(CRIAR_USUARIO_ENDPOINT)
                .then()
                .extract().response();
        deveBuscarUmUsuarioPorId();
        return response;
    }
    public Response deveAtualizarTodosOsUsuario(){
        Usuario usuario = new Usuario("joao", "male", GeradorEmail.generateRandomEmail(), "active");
        int idEsperado =  given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .when().get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().statusCode(200).extract().path("id[0]");
        System.out.println(idEsperado);
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .pathParam("id", idEsperado)
                .body(usuario)
                .when()
                .put(ATUALIZA_TODO_O_USUARIO_ENDPOINT)
                .then().statusCode(200).extract().response();
        System.out.println(response.asString());
        return response;

    }
    public Response deveAtualizarUmCampoDoUsuario(){
        Usuario usuario = new Usuario("Carolina Helena", "female", GeradorEmail.generateRandomEmail(), "active");
        int idEsperado =  given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .when().get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().statusCode(200).extract().path("id[0]");
        System.out.println(idEsperado);
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .pathParam("id", idEsperado)
                .body(usuario)
                .when()
                .patch(ATUALIZA_UM_CAMPO_USUARIO_ENDPOINT)
                .then().statusCode(200).extract().response();
        System.out.println(response.asString());
        return response;
    }
    public Response deveExcluirUmUsuario(){
        deveCriarUmUsuario();
        int idEsperado =  given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .when().get(LISTAR_TODOS_USUARIOS_ENDPOINT)
                .then().statusCode(200).extract().path("id[0]");
        System.out.println(idEsperado);
        Response response = RestAssured.given()
                .header("Authorization", "Bearer " + ACESS_TOKEN)
                .pathParam("id", idEsperado)
                .when()
                .delete(EXCLUIR_USUARIO_POR_ID_ENDPOINT)
                .then().statusCode(204).extract().response();
        //System.out.println(response.asString());
        //deveBuscarUmUsuarioPorId();
        return response;
    }


}
