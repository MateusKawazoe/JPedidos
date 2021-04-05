package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

@TestMethodOrder(OrderAnnotation.class)
public class LoginTest {

  LoginController login = new LoginController();
  CRUD crud = new CRUD();
  String result;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  @Order(1)
  public void cadastrarLoginTest() {
    result = login.cadastrar("LucasShz", "Mateus123");
    assertEquals("false", result);
    login.sair("LucasShz");
  }

  @Test
  @Order(2)
  public void usuariojaCadastrado() {
    result = login.cadastrar("LucasShz", "Mateus123");
    assertEquals("Usuário já existe!", result);
  }

  @Test
  @Order(3)
  public void senhaInvalidaTest() {
    result = login.entrar("LucasShz", "123123");
    assertEquals("Senha inválida!", result);
  }

  @Test
  @Order(4)
  public void loginRealizadoComSucessoTest() {
    result = login.entrar("LucasShz", "Mateus123");
    assertEquals("Login realizado com sucesso!", result);
  }

  @Test
  @Order(5)
  public void deletarUsuarioTest() {
    result = login.deletar("LucasShz");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE login AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }

  @Test
  @Order(6)
  public void usuarioNaoExisteTest() {
    result = login.entrar("LucasShz", "Loucura");
    assertEquals("Usuário não existe!", result);
  }

  @Test
  @Order(7)
  public void entrarException() {
    result = login.entrar(null, "Loucura");
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(8)
  public void cadastrarException() {
    result = login.cadastrar(null, "Loucura");
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(9)
  public void deletarException() {
    result = login.deletar(null);
    assertEquals("Usuário não existe!", result);
    exception.expect(NullPointerException.class);
  }
}

