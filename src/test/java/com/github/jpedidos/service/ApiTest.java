package com.github.jpedidos.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ApiTest {

  @Test
  public void conexaoValidaTest() {
    Api api = new Api();

    api.conectar();
    assertEquals("Conectado", api.getConexao());

    try {
        api.con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
