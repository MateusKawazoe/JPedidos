package com.github.jpedidos.controller;

import com.github.jpedidos.auth.MD5;
import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;

public class Login {

  Connection connection = new Connection();
  CRUD crud = new CRUD();
  MD5 md = new MD5();

  public String entrar(String usuario, String senha) {
    try {
      connection =
        crud.buscar(
          "SELECT login_password FROM login WHERE login_username = '" +
          usuario +
          "'"
        );

      if (connection.getRs() != null) {
        if (
          connection.getRs().getString("login_password").trim().equals(
          md.encrypt(senha))
        ) {
          crud.inserirModificarDeletar(
            "UPDATE login SET login_token = '" +
            md.encrypt(usuario + senha) +
            "' WHERE login_username = '" +
            usuario +
            "'"
          );
          connection.close();
          return "Login realizado com sucesso!";
        }
      } else {
        connection.close();
        return "Usuário não existe!";
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    connection.close();
    return "Senha inválida!";
  }

  public String cadastrar(String usuario, String senha) {
    try {
      connection =
        crud.buscar(
          "SELECT login_username FROM login WHERE login_username = '" +
          usuario +
          "'"
        );
      if (connection.getRs() != null) {
        connection.close();
        return "Usuário já existe!";
      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return crud.inserirModificarDeletar(
      "INSERT INTO login(login_username, login_password, login_token)" +
      " values('" +
      usuario +
      "', '" +
      md.encrypt(senha) +
      "', '" +
      md.encrypt(usuario + senha) +
      "')"
    );
  }

  public String deletar(String usuario) {
    try {
      return crud.inserirModificarDeletar("DELETE FROM login WHERE login_username = '"+ usuario +"'");
    } catch (Exception e) {
        System.out.println(e);
    }

    return "Usuário não existe!";
  }

  public String sair(String usuario) {
    return crud.inserirModificarDeletar(
      "UPDATE login SET login_token = '' WHERE login_username = '" +
      usuario +
      "'"
    );
  }
}
