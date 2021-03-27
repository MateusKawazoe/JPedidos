package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class LoginTest {

  LoginController login = new LoginController();
  CRUD crud = new CRUD();
  String result;

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
}
