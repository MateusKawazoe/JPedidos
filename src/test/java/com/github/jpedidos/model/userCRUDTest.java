package com.github.jpedidos.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class userCRUDTest {

  CRUD crud = new CRUD();
  Connection connection;

  @Test
  public void CRUDTest() {
    String result = crud.inserirModificarDeletar(
      "INSERT INTO login(login_username, login_password, login_token)" +
      " values('Mateuskwz', 'c8751b29c9bd8ef0b7fe9be5def40a39', '2d3855ed596b1a553922e4e5a14abda0')"
    );
    assertEquals("false", result);

    try {
      connection =
        crud.buscar(
          "SELECT login_id FROM login WHERE login_username = 'Mateuskwz'"
        );

      if (connection.getRs() != null) {
        result =
          crud.inserirModificarDeletar(
            "INSERT INTO user(user_name, user_email, user_type, login_login_id)" +
            " values('Mateus Kawazoe', 'matkawazoe@gmail.com', 'Funcionario', " +
            connection.getRs().getInt("login_id") +
            ")"
          );
        connection.close();
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "UPDATE login SET login_username = 'Lux' WHERE login_username = 'Mateuskwz'"
      );
    assertEquals("false", result);

    try {
      connection =
        crud.buscar(
          "SELECT login_username FROM login WHERE login_username = 'Lux'"
        );

      if (connection.getRs() != null) {
        result = connection.getRs().getString("login_username");
        assertEquals("Lux", result);
      }
      connection.close();
    } catch (Exception e) {
      System.out.print(e);
    }
    assertEquals("Lux", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM user WHERE user_name = 'Mateus Kawazoe'"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM login WHERE login_username = 'Lux'"
      );
    assertEquals("false", result);
  }
}
