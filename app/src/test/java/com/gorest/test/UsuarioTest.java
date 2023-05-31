package com.gorest.test;


import com.gorest.GeradorEmail;
import com.gorest.controller.UsuarioController;
import com.gorest.dominio.Usuario;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsuarioTest extends BaseTest {

    private UsuarioController userController;

    public UsuarioTest(){
        userController = new UsuarioController();
    }
    @Test
    public void deveListarTodosUsuarios() {
        Usuario[] usuarios = userController.deveListarTodosUsuarios();
        assertThat(usuarios.length, equalTo(10));
    }

    @Test
    public void deveCriarUmUsuarioComSucesso() {
        Usuario usuario = new Usuario("carol", "female", GeradorEmail.generateRandomEmail(), "active");
        userController.deveCriarUmUsuarioComSucesso(usuario);
        assertEquals("name", equalTo("carol"));

    }

    @Test
    public void deveBuscarUsuarioPorId() {
        String id = "";
        Usuario usuario = userController.deveBuscarUsuarioPorId(id);
        assertThat(usuario.getName(), equalTo("carol"));
    }

    @Test
    public void deveAtualizarUsuario(String id) {
        Usuario usuario = new Usuario("Joao", "male", GeradorEmail.generateRandomEmail(), "active");
        userController.deveAtualizarUsuario(usuario, id);

    }

    @Test
    public void deveExcluirUsuario(String id) {
        userController.deveExcluirUsuario(id);
    }


}
