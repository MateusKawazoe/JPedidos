package com.github.jpedidos.model;

import com.github.jpedidos.service.Api;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CRUD {

  private Api api = new Api();
  private Connection connection = new Connection();

  public String inserirModificarDeletar(String query) {
    api.conectar();

    try (PreparedStatement st = api.con.prepareStatement(query)) {
      String aux = st.execute() + "";
      fecharConexao();
      return aux;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return "true";
  }

  public Connection buscar(String query) {
    api.conectar();

    try {
      connection.setApi(api);
      connection.setSt(api.con.prepareStatement(query));
      connection.setRs(connection.getSt().executeQuery());
      if (connection.getRs().next()) {
        return connection;
      }
    } catch (SQLException e) {
      System.out.println(e);
    }
    return null;
  }

  private void fecharConexao() {
    try {
      api.con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
