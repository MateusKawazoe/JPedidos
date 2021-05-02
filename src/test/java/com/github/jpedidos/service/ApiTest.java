package com.github.jpedidos.service;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import org.junit.Rule;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class ApiTest {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void conexaoValidaTest() {
    Api api = new Api();

    api.conectar("Amendobobo1@#", "teste");
    assertEquals("Conectado", api.getConexao());

    try {
      api.con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  @Test
  public void conexaoInvalidaTest() {
    Api api = new Api();

    api.conectar("Amendobobo", "mydb");
    exception.expect(SQLException.class);
  }
}
