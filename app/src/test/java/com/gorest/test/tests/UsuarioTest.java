package com.gorest.test.tests;

import com.gorest.test.service.UsuarioService;
import com.gorest.test.validacao.RestAssuredValidator;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.hamcrest.core.IsNull.notNullValue;

public class UsuarioTest extends BaseTest{

    @Test
    public void testDeveBucarTodosOsUsuarios(){
        UsuarioService usuarioService = new UsuarioService();
        Response response = usuarioService.deveBucarTodosOsUsuarios();
        RestAssuredValidator validator = new RestAssuredValidator(response);
        validator.verificarStatusCode(200);
        validator.verificarCampoExistente("id");

    }
    @Test
    public void testDeveBuscarUmUsuarioPorId(){
        UsuarioService usuarioService = new UsuarioService();
        //int id = 2574312;
        Response response = usuarioService.deveBuscarUmUsuarioPorId();
        RestAssuredValidator validator = new RestAssuredValidator(response);
        validator.verificarStatusCode(200);
        validator.verificarCampoExistente("id");
    }
    @Test
    public void testDeveCriarUmUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        Response response = usuarioService.deveCriarUmUsuario();

        RestAssuredValidator validator = new RestAssuredValidator(response);

        validator.verificarStatusCode(201);
        validator.verificarCampoExistente("id");
        validator.verificarCampo("name", "carolina");

    }
    @Test
    public void testDeveAtualizarTodosOsUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        Response response = usuarioService.deveAtualizarTodosOsUsuario();

        RestAssuredValidator validator = new RestAssuredValidator(response);
        validator.verificarStatusCode(200);
        validator.verificarCampoExistente("id");
        validator.verificarCampo("name", "joao");
        validator.verificarCampo("gender", "male");
        validator.verificarCampo("status", "active");
    }
    @Test
    public void testDeveAtualizarUmCampoDoUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        Response response = usuarioService.deveAtualizarUmCampoDoUsuario();

        RestAssuredValidator validator = new RestAssuredValidator(response);
        validator.verificarStatusCode(200);
        validator.verificarCampoExistente("id");
        validator.verificarCampo("name", "Carolina Helena");
        validator.verificarCampo("gender", "female");

    }
    @Test
    public void testDeveExcluirUmUsuario(){
        UsuarioService usuarioService = new UsuarioService();
        Response response = usuarioService.deveExcluirUmUsuario();

        RestAssuredValidator validator = new RestAssuredValidator(response);
        validator.verificarStatusCode(204);
        validator.verificarCampo("", "");

    }
}
