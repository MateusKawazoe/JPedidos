package com.github.jpedidos.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.github.jpedidos.model.CRUD;
import org.junit.jupiter.api.Test;

public class ProductTest {

  @Test
  public void productTest() {
    Product product = new Product();
    CRUD crud = new CRUD();

    String result = product.cadastrar(
      "Luva Térmica",
      "Luva térmica em formato de canguru. Aguenta até 300ºC",
      (float)32.99
    );
    assertEquals("Produto cadastrado com sucesso!", result);

    result = product.cadastrar(
      "Luva Térmica",
      "Luva térmica em formato de canguru. Aguenta até 300ºC",
      (float)32.99
    );
    assertEquals("Produto já cadastrado!", result);

    result = product.alterar("Luva Térmica", "Loucura Loucura Loucura", (float)0);
    assertEquals("Produto alterado com sucesso!", result);

    try {
      result =
        product.listar("Luva Térmica").getRs().getString("product_description");
      assertEquals("Loucura Loucura Loucura", result);
    } catch (Exception e) {
      System.out.println(e);
    }

    result = product.deletar("Luva Térmica");
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar("ALTER TABLE product AUTO_INCREMENT = 1");
    assertEquals("false", result);
  }
}
