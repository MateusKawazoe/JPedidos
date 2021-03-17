package com.github.jpedidos.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Api {

  public Connection con = null;
  private String conexao = "Não conectado";

  public void conectar() {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver"); // Seleciona o driver para acesso ao banco de dados.
      con =
        DriverManager.getConnection(
          "jdbc:mysql://localhost/mydb",
          "root",
          "Amendobobo1@#"
        ); // Carrega o driver MYSQL para conexão com o banco de dados.
      setConexao("Conectado");
    } catch (ClassNotFoundException ex) {
      System.out.println(
        "Classe não encontrada, adicione o driver nas bibliotecas."
      );
    } catch (SQLException e) {
      System.out.print(e);
      throw new RuntimeException(e);
    }
  }

  public String getConexao() {
    return conexao;
  }

  public void setConexao(String conexao) {
    this.conexao = conexao;
  }
}
