package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

  ProductController product = new ProductController();
  CRUD crud = new CRUD();
  String result;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  @Order(1)
  public void cadastrarProdutoTest() {
    result =
      product.cadastrar(
        "Luva Térmica",
        "Luva térmica em formato de canguru. Aguenta até 300ºC",
        (float) 32.99
      );
    assertEquals("Produto cadastrado com sucesso!", result);
  }

  @Test
  @Order(2)
  public void produtoJaExisteTest() {
    result =
      product.cadastrar(
        "Luva Térmica",
        "Luva térmica em formato de canguru. Aguenta até 300ºC",
        (float) 32.99
      );
    assertEquals("Produto já cadastrado!", result);
  }

  @Test
  @Order(3)
  public void alterarDescricao() {
    result =
      product.alterar("Luva Térmica", "Loucura Loucura Loucura", (float) 0);
    assertEquals("Produto alterado com sucesso!", result);
  }

  @Test
  @Order(4)
  public void alterarPreco() {
    result =
      product.alterar("Luva Térmica", "", (float) 15.99);
    assertEquals("Produto alterado com sucesso!", result);
  }

  @Test
  @Order(5)
  public void listarProdutoTest() {
    try {
      result =
        product.listar("Luva Térmica").getRs().getString("product_description");
      assertEquals("Loucura Loucura Loucura", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(6)
  public void listarTodosTest() {
    try {
      assertEquals(false, product.listar("").getRs().next());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(7)
  public void idProduto() {
    try {
      result = "" + product.idProduto("Luva Térmica");
      assertEquals("1", result);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  @Order(8)
  public void deletarProdutoTest() {
    result = product.deletar("Luva Térmica");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE product AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }

  @Test
  @Order(9)
  public void cadastrarException() {
    product.cadastrar(null, "Oie", (float) 0);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(10)
  public void alterarException() {
    product.alterar(null, "", (float) 0);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(11)
  public void idProdutoException() {
    product.idProduto(null);
    exception.expect(NullPointerException.class);
  }

  @Test
  @Order(12)
  public void deletarException() {
    result = product.deletar(null);
    exception.expect(NullPointerException.class);
  }
}
