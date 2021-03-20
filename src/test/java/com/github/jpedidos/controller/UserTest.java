package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Test;

public class UserTest {
    CRUD crud = new CRUD();

  @Test
  public void userTest() {
    UserController user = new UserController();

    String result = user.cadastrar(
      "Mateus Kawazoe",
      "matkawazoe@gmail.com",
      "Funcionario",
      991095986,
      "Mateuskwz",
      "Mateus123"
    );

    assertEquals("Usuário cadastrado com sucesso!", result);
    result =
      user.cadastrar(
        "Mateus Kawazoe",
        "matkawazoe@gmail.com",
        "Funcionario",
        991095986,
        "",
        ""
      );
    assertEquals("E-mail já cadastrado!", result);

    result = user.alterar("matkawazoe@gmail.com", 0, "Cliente");
    assertEquals("Usuário alterado com sucesso!", result);

    try {
      result =
        user.listar("matkawazoe@gmail.com").getRs().getString("user_name");
      assertEquals("Mateus Kawazoe", result);
    } catch (Exception e) {
      System.out.println(e);
    }

    result = user.deletar("matkawazoe@gmail.com");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE user AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }
}
