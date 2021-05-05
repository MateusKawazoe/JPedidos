package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;

@TestMethodOrder(OrderAnnotation.class)
public class UserTest {

  UserController user = new UserController("teste");
  CRUD crud = new CRUD("teste");
  String result;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

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
  public void cadastrarUsuarioELoginTest() {
    result =
      user.cadastrar(
        "Lucas Shizuno",
        "luquinhas@gmail.com",
        "Funcionario",
        991095986,
        "LucasShizu",
        "cobrinha"
      );
    assertEquals("Usuário cadastrado com sucesso!", result);
  }

  @Test
  @Order(3)
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
  @Order(4)
  public void loginJaCadastrado() {
    result =
      user.cadastrar(
        "Lucas Shizuno",
        "luquinhasoioi@gmail.com",
        "Funcionario",
        991095986,
        "LucasShizu",
        "cobrinha"
      );
    assertEquals("Usuário já existe!", result);
  }

  @Test
  @Order(5)
  public void usuarioAlteradoComSucessoTest() {
    result = user.alterar("matkawazoe@gmail.com", 0, "");
    assertEquals("Usuário alterado com sucesso!", result);
  }

  @Test
  @Order(6)
  public void usarioExisteTest() {
    try {
      result =
        user.listar("Mateus Kawazoe", 991095986).getRs().getString("user_name");
      assertEquals("Mateus Kawazoe", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(7)
  public void listarTodosTest() {
    try {
      assertEquals(true, user.listar("", 0).getRs().next());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(8)
  public void deletarUsuarioTest() {
    result = user.deletar("matkawazoe@gmail.com");
    assertEquals("false", result);

    crud.inserirModificarDeletar("ALTER TABLE user AUTO_INCREMENT = 1");
  }

  @Test
  @Order(9)
  public void deletarUsuario2Test() {
    result = user.deletar("luquinhas@gmail.com");
    assertEquals("false", result);

    crud.inserirModificarDeletar("DELETE FROM login WHERE login_id > 0");
    crud.inserirModificarDeletar("ALTER TABLE login AUTO_INCREMENT = 1");
  }

  @Test
  @Order(10)
  public void cadastrarException() {
    result =
      user.cadastrar(
        null,
        "matkawazoe@gmail.com",
        "Funcionario",
        991095986,
        "",
        ""
      );
    assertEquals("", result);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(11)
  public void alterarException() {
    result = user.alterar(null, 88, "Funcionario");
    assertEquals("", result);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(12)
  public void deletarException() {
    result = user.deletar(null);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(13)
  public void deletarUsuarioInexistenteTest() {
    result = user.deletar("matkawazoeaaaaa@gmail.com");
    assertEquals("Usuário não existe!", result);
  }
}
