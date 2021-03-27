package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import java.text.DecimalFormat;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class OrderTest {

  DecimalFormat df = new DecimalFormat("0.00");
  ProductController product = new ProductController();
  OrderController order = new OrderController();
  UserController user = new UserController();
  CRUD crud = new CRUD();
  Connection connection;
  String result;

  @Test
  @Order(1)
  public void cadastrarPedidoTest() {
    user.cadastrar(
      "Mateus Kawazoe",
      "leaozinho_leao@gmail.com",
      "Funcionario",
      991095986,
      "",
      ""
    );
    result = order.cadastrarPedido(1);
    assertEquals("false", result);
  }

  @Test
  @Order(2)
  public void adicionarProdutoAoPedidoTest() {
    product.cadastrar("Luva de Borracha", "Loucura Loucura Loucura", (float) 33.88);
    order.adicionarProduto("Luva de Borracha", 7, 1, 1);
    try {
      connection = order.listar(1);
      assertEquals(
        df.format(7 * 33.88),
        df.format(connection.getRs().getFloat("order_value"))
      );
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(3)
  public void removerProdutoAoPedidoTest() {
    order.removerProduto("Luva de Borracha", 2, 1, 1);
    try {
      connection = order.listar(1);
      assertEquals(
        df.format(5 * 33.88),
        df.format(connection.getRs().getFloat("order_value"))
      );
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(4)
  public void removerMaisProdutosDoqueExistemTest() {
    result = order.removerProduto("Luva de Borracha", 10, 1, 1);
    assertEquals("Operação inválida!", result);
  }

  @Test
  @Order(5)
  public void removerAQuantidadeExataDeProdutosTest() {
    result = order.removerProduto("Luva de Borracha", 5, 1, 1);
    assertEquals("false", result);
  }

  @Test
  @Order(6)
  public void deletarPedido() {
    result = order.deletar(1);
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "ALTER TABLE client_order AUTO_INCREMENT = 1"
      );
    assertEquals("false", result);

    user.deletar("leaozinho_leao@gmail.com");
    crud.inserirModificarDeletar("ALTER TABLE user AUTO_INCREMENT = 1");
    product.deletar("Luva de Borracha");
    crud.inserirModificarDeletar("ALTER TABLE product AUTO_INCREMENT = 1");
  }
}
