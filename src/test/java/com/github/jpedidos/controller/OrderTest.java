package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import java.text.DecimalFormat;
import org.junit.Rule;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.rules.ExpectedException;

@TestMethodOrder(OrderAnnotation.class)
public class OrderTest {

  DecimalFormat df = new DecimalFormat("0.00");
  ProductController product = new ProductController("teste");
  OrderController order = new OrderController("teste");
  UserController user = new UserController("teste");
  CRUD crud = new CRUD("teste");
  Connection connection;
  String result;
  int userid = 0;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  @Order(1)
  public void cadastrarPedidoTest() {
    user.cadastrar(
      "Luquinhas",
      "leaozinho_leao@gmail.com",
      "Funcionario",
      991095986,
      "",
      ""
    );
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      result = order.cadastrarPedido(userid);
      assertEquals("false", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(2)
  public void adicionarProdutoAoPedidoTest() {
    product.cadastrar(
      "Luva de Borracha",
      "Loucura Loucura Loucura",
      (float) 33.88
    );
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      order.adicionarProduto("Luva de Borracha", 7, 1, userid);
      connection = order.listar(userid);
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
  public void aumentarAQuantidadeDeUmProdutoTest() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      order.adicionarProduto("Luva de Borracha", 2, 1, userid);
      connection = order.listar(userid);
      assertEquals(
        df.format(9 * 33.88),
        df.format(connection.getRs().getFloat("order_value"))
      );
      order.listar(0);
      order.buscarUmPedido(1);
      order.ultimoId();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(4)
  public void removerProdutoAoPedidoTest() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      order.removerProduto("Luva de Borracha", 2, 1, userid);
      connection = order.listar(userid);
      assertEquals(
        df.format(7 * 33.88),
        df.format(connection.getRs().getFloat("order_value"))
      );
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(5)
  public void removerMaisProdutosDoQueExistemTest() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      result = order.removerProduto("Luva de Borracha", 10, 1, userid);
      assertEquals("Operação inválida!", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(6)
  public void removerAQuantidadeExataDeProdutosTest() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      result = order.removerProduto("Luva de Borracha", 7, 1, userid);
      assertEquals("false", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(7)
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

  @Test
  @Order(8)
  public void adicionarProdutoInexistente() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      result = order.adicionarProduto("Luvinha", 7, 1, userid);
      assertEquals("Produto não existe!", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(9)
  public void removerProdutoInexistente() {
    try {
      userid =
        user.listar("Luquinhas", 991095986).getRs().getInt("user_id");
      result = order.removerProduto("Luvinha", 7, 1, userid);
      assertEquals("Produto não existe!", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(10)
  public void adicionarProdutoException() {
    result = order.adicionarProduto("", 7, 1, userid);
    assertEquals("Existem campos vazios!", result);
  }

  @Test
  @Order(11)
  public void removerQuantidadeProdutoException() {
    result = order.removerProduto("Loucura", 0, 1, userid);
    assertEquals("Existem campos vazios!", result);
  }

  @Test
  @Order(12)
  public void ultimoIDException() {
    order = new OrderController("segredo");
    result = order.ultimoId() + "";
    assertEquals("0", result);
  }

  @Test
  @Order(13)
  public void deletarException() {
    result = order.deletar(0);
    assertEquals("Id inválido!", result);
  }
}
