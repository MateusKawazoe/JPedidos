package com.github.jpedidos.controller;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;

public class UserController {

  LoginController login = new LoginController();
  CRUD crud = new CRUD();
  Connection connection;
  String result;

  public String cadastrar(
    String nome,
    String email,
    String tipo,
    int telefone,
    String usuario,
    String senha
  ) {
    try {
      connection =
        crud.buscar("SELECT * FROM user WHERE user_email = '" + email + "'");

      if (connection != null) {
        connection.close();
        return "E-mail já cadastrado!";
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    if (usuario.equals("")) {
      crud.inserirModificarDeletar(
        "INSERT INTO user (user_name, user_email, user_type, user_phone) " +
        "VALUES('" +
        nome +
        "', '" +
        email +
        "', '" +
        "Cliente" +
        "', " +
        telefone
      );

      return "Usuário cadastrado com sucesso!";
    } else {
      result = login.cadastrar(usuario, senha);

      if (result.equals("false")) {
        crud.inserirModificarDeletar(
          "INSERT INTO user (user_name, user_email, user_type, user_phone, login_login_id) " +
          "VALUES('" +
          nome +
          "', '" +
          email +
          "', '" +
          tipo +
          "', " +
          telefone +
          ", (SELECT MAX(login_id) FROM login))"
        );

        return "Usuário cadastrado com sucesso!";
      }
    }

    return result;
  }

  public String alterar(String email, int telefone, String tipo) {
    String auxTipo = tipo;
    int auxTelefone = telefone;

    try {
      connection =
        crud.buscar(
          "SELECT user_type, user_phone FROM user WHERE user_email = '" +
          email +
          "'"
        );

      if (auxTipo.equals("")) auxTipo =
        connection.getRs().getString("user_type");
      if (auxTelefone == 0) auxTelefone =
        connection.getRs().getInt("user_phone");
    } catch (Exception e) {
      System.out.println(e);
    }

    result =
      crud.inserirModificarDeletar(
        "UPDATE user SET user_phone = '" +
        auxTelefone +
        "', user_type ='" +
        auxTipo +
        "' " +
        "WHERE user_email = '" +
        email +
        "'"
      );

    if (result.equals("false")) {
      return "Usuário alterado com sucesso!";
    }

    return result;
  }

  public Connection listar(String email) {
    if (email.equals("")) {
      return crud.buscar("Select * FROM user");
    } else {
      return crud.buscar(
        "Select * FROM user WHERE user_email = '" + email + "'"
      );
    }
  }

  public String deletar(String email) {
    try {
      connection =
        crud.buscar(
          "Select login_login_id FROM user WHERE user_email = '" + email + "'"
        );

      crud.inserirModificarDeletar(
        "DELETE FROM user WHERE user_email = '" + email + "'"
      );

      if (connection.getRs() != null) {
        return crud.inserirModificarDeletar(
          "DELETE FROM login WHERE login_id = '" +
          connection.getRs().getInt("login_login_id") +
          "'"
        );
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return "Usuário não existe!";
  }
}
