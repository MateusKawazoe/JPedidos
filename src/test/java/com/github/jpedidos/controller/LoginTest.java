package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Test;

public class LoginTest {

  @Test
  public void loginTest() {
    Login login = new Login();
    CRUD crud = new CRUD();

    String result = login.cadastrar("Mateuskwz", "Mateus123");

    assertEquals("false", result);
    result = login.cadastrar("Mateuskwz", "Mateus123");
    assertEquals("Usuário já existe!", result);
    result = login.entrar("Mateuskwz", "Mateus123");
    assertEquals("Login realizado com sucesso!", result);
    result = login.entrar("Mateuskwz", "123123");
    assertEquals("Senha inválida!", result);
    login.sair("Mateuskwz");
    result = login.deletar("Mateuskwz");
    assertEquals("false", result);
    result =
      crud.inserirModificarDeletar("ALTER TABLE login AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }
}
