package com.github.jpedidos.model;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;

public class productCRUDTest {

  CRUD crud = new CRUD("teste");
  Connection connection;

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void inserirModificarDeletarInvalidoTest() {
    crud.inserirModificarDeletar(null);
    exception.expect(SQLException.class);
  }

  @Test
  public void buscarInvalidoTest() {
    crud.buscar(null);
    exception.expect(SQLException.class);
  }

  @Test
  public void fecharConexaoInvalida() {
    crud.fecharConexao();
    exception.expect(NullPointerException.class);
  }

  @Test
  public void fecharConexaoConnection() {
    connection = new Connection();
    connection.close();
    exception.expect(NullPointerException.class);
  }
}
