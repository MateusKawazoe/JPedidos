package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

  ProductController product = new ProductController();
  CRUD crud = new CRUD();
  String result;

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
  public void alterarProdutoTest() {
    result =
      product.alterar("Luva Térmica", "Loucura Loucura Loucura", (float) 0);
    assertEquals("Produto alterado com sucesso!", result);
  }

  @Test
  @Order(4)
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
  @Order(5)
  public void deletarProdutoTest() {
    result = product.deletar("Luva Térmica");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE product AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }
}
