package com.github.jpedidos.service;

import java.sql.Connection;
import java.sql.DriverManager;

public class Api {

  public Connection con = null;
  private String conexao = "Não conectado";

  public void conectar(String senha) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver"); // Seleciona o driver para acesso ao banco de dados.
      con =
        DriverManager.getConnection(
          "jdbc:mysql://localhost/mydb",
          "root",
          senha
        ); // Carrega o driver MYSQL para conexão com o banco de dados.
      setConexao("Conectado");
    }  catch (Exception e) {
      System.out.print(e);
    }
  }

  public String getConexao() {
    return conexao;
  }

  public void setConexao(String conexao) {
    this.conexao = conexao;
  }
}
