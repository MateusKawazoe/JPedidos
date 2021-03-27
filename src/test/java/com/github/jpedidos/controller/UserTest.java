package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class UserTest {

  UserController user = new UserController();
  CRUD crud = new CRUD();
  String result;

  @Test
  @Order(1)
  public void cadastrarUsuarioTest() {
    result =
      user.cadastrar(
        "Mateus Kawazoe",
        "matkawazoe@gmail.com",
        "Funcionario",
        991095986,
        "",
        ""
      );
    assertEquals("Usuário cadastrado com sucesso!", result);
  }

  @Test
  @Order(2)
  public void emailJaCadastradoTest() {
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
  }

  @Test
  @Order(3)
  public void usuarioAlteradoComSucessoTest() {
    result = user.alterar("matkawazoe@gmail.com", 0, "Cliente");
    assertEquals("Usuário alterado com sucesso!", result);
  }

  @Test
  @Order(4)
  public void usarioExisteTest() {
    try {
      result =
        user.listar("matkawazoe@gmail.com").getRs().getString("user_name");
      assertEquals("Mateus Kawazoe", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(5)
  public void deletarUsuarioTest() {
    result = user.deletar("matkawazoe@gmail.com");
    assertEquals("false", result);

    crud.inserirModificarDeletar("ALTER TABLE user AUTO_INCREMENT = 1");
  }
}
