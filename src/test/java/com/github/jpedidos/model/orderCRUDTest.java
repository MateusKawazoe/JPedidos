package com.github.jpedidos.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class orderCRUDTest {

  CRUD crud = new CRUD();
  Connection connection, connection2;

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
            "INSERT INTO user(user_name, user_email, user_type, user_phone, login_login_id)" +
            " values('Mateus Kawazoe', 'matkawazoe@gmail.com', 'Funcionario', 991095986, " +
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
        "INSERT INTO product(product_name, product_description, product_price)" +
        " values('Luva Térmica', 'Luva térmica em formato de canguru. Aguenta até 300ºC', 32.99)"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "INSERT INTO product(product_name, product_description, product_price)" +
        " values('Femow', 'Femow térmica em formato de canguru. Aguenta até 300ºC', 82.99)"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "INSERT INTO product(product_name, product_description, product_price)" +
        " values('PH', 'PH térmica em formato de canguru. Aguenta até 300ºC', 12.99)"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "INSERT INTO client_order(order_status, user_user_id)" + " values(1, 1)"
      );
    assertEquals("false", result);

    try {
      connection = crud.buscar("SELECT * FROM product");
      float aux = 0;
      int soma = 1;
      while (connection.getRs().next()) {
        aux = connection.getRs().getFloat("product_price") * (soma * 2);
        result =
          crud.inserirModificarDeletar(
            "INSERT INTO order_item(order_item_qtd, product_product_id, client_order_order_id) " +
            "VALUES(" +
            soma +
            ", " +
            connection.getRs().getInt("product_id") +
            ", 1)"
          );
        assertEquals("false", result);

        soma++;
      }

      result =
        crud.inserirModificarDeletar(
          "UPDATE client_order SET order_value =" + aux + " WHERE order_id = 1"
        );
      assertEquals("false", result);
    } catch (Exception e) {}

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

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM order_item WHERE client_order_order_id > 0"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM client_order WHERE order_id > 0"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE client_order AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM user WHERE user_name = 'Mateus Kawazoe'"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE user AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM login WHERE login_username = 'Lux'"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE login AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM product WHERE product_id > 0"
      );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE product AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);
  }
}
