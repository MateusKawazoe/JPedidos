package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Test;

public class OrderTest {

  @Test
  public void orderTest() {
    DecimalFormat df = new DecimalFormat("0.00");
    Product product = new Product();
    Order order = new Order();
    CRUD crud = new CRUD();
    Connection connection;
    User user = new User();

    String result = user.cadastrar(
      "Mateus Kawazoe",
      "matkawazoe@gmail.com",
      "Funcionario",
      991095986,
      "Mateuskwz",
      "Mateus123"
    );
    assertEquals("Usuário cadastrado com sucesso!", result);

    result = order.cadastrarPedido(1);
    assertEquals("false", result);

    result =
      product.cadastrar(
        "Luva Térmica",
        "Loucura Loucura Loucura",
        (float) 33.88
      );
    assertEquals("Produto cadastrado com sucesso!", result);

    result = order.adicionarProduto("Luva Térmica", 7, 1);
    assertEquals("false", result);

    result = order.removerProduto("Luva Térmica", 2, 1);
    assertEquals("false", result);

    try {
      connection = order.listar(1);
      assertEquals(
        df.format(5 * 33.88),
        df.format(connection.getRs().getFloat("order_value"))
      );
    } catch (Exception e) {
      System.out.println(e);
    }

    result = order.deletar(1);
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE client_order AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);

    result = user.deletar("matkawazoe@gmail.com");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("DELETE FROM login WHERE login_id > 0");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE login AUTO_INCREMENT = 1");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE user AUTO_INCREMENT = 1");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("DELETE FROM product WHERE product_id > 0");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE product AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }
}
