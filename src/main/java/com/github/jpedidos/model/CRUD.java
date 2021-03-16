package com.github.jpedidos.model;

import com.github.jpedidos.service.Api;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD {

  private Api api = new Api();

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

  public String buscar(String query) {
    api.conectar();

    try (PreparedStatement st = api.con.prepareStatement(query);
      ResultSet rs = st.executeQuery()
    ) {
      String aux = "";

      if(rs.next()) {
        aux = rs.getString("login_username");
      }
      fecharConexao();
      return aux;
    } catch (SQLException e) {
      System.out.println(e);
    }
    return "true";
  }

  private void fecharConexao() {
    try {
      api.con.close();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
