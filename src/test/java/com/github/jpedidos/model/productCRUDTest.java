package com.github.jpedidos.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class productCRUDTest {

  CRUD crud = new CRUD();
  Connection connection;

  @Test
  public void CRUDTest() {
    String result = crud.inserirModificarDeletar(
      "INSERT INTO product(product_name, product_description, product_price)" +
      " values('Luva Térmica', 'Luva térmica em formato de canguru. Aguenta até 300ºC', 32.99)"
    );
    assertEquals("false", result);

    result =
      crud.inserirModificarDeletar(
        "UPDATE product SET product_price = 29.99 WHERE product_name = 'Luva Térmica'"
      );
    assertEquals("false", result);

    try {
      connection =
        crud.buscar(
          "SELECT product_description FROM product WHERE product_name = 'Luva Térmica'"
        );

      if (connection.getRs() != null) {
        result = connection.getRs().getString("product_description");
        assertEquals(
          "Luva térmica em formato de canguru. Aguenta até 300ºC",
          result
        );
      }
      connection.close();
    } catch (Exception e) {
      System.out.print(e);
    }

    result =
      crud.inserirModificarDeletar(
        "DELETE FROM product WHERE product_name = 'Luva Térmica'"
      );
    assertEquals("false", result);
  }
}
