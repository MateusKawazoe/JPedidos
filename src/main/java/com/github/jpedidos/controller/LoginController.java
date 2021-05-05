package com.github.jpedidos.controller;

import com.github.jpedidos.auth.MD5;
import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import com.github.jpedidos.validate.*;
import java.sql.SQLException;

public class LoginController {

  Connection connection = new Connection();
  MD5 md = new MD5();
  CRUD crud;
  String error = "";

  public LoginController(String bd) {
    try {
      if (
        bd.equals("") || !bd.equals("teste") && !bd.equals("mydb")
      ) throw new InvalidConnextionException("Nome do banco de dados inválido");

      crud = new CRUD(bd);
    } catch (InvalidConnextionException err) {
      error = err.getMessage();
    }
  }

  public String entrar(String usuario, String senha) {
    if (!error.equals("")) return error;

    try {
      if (usuario.equals("")) throw new EmptyVariableException(
        "Usuário vazio!"
      ); else if (senha.equals("")) throw new EmptyVariableException(
        "Senha vazia!"
      );

      connection =
        crud.buscar(
          "SELECT login_password FROM login WHERE login_username = '" +
          usuario +
          "'"
        );

      if (connection != null) {
        if (
          connection
            .getRs()
            .getString("login_password")
            .trim()
            .equals(md.encrypt(senha))
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
        return "Usuário não existe!";
      }
    } catch (EmptyVariableException err) {
      return err.getMessage();
    } catch (SQLException err) {
      return err.getMessage();
    }
    return "Senha inválida!";
  }

  public String cadastrar(String usuario, String senha) {
    if (!error.equals("")) return error;

    try {
      if (usuario.equals("")) throw new EmptyVariableException(
        "Usuário vazio!"
      ); else if (senha.equals("")) throw new EmptyVariableException(
        "Senha vazia!"
      );

      connection =
        crud.buscar(
          "SELECT login_username FROM login WHERE login_username = '" +
          usuario +
          "'"
        );
      if (connection != null) {
        connection.close();
        return "Usuário já existe!";
      }
    } catch (EmptyVariableException err) {
      return err.getMessage();
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

  public int loginId(String usuario) {
    if (!error.equals("")) return -1;

    try {
      if (usuario.equals("")) throw new EmptyVariableException(
        "Usuário vazio!"
      );

      return crud
        .buscar(
          "SELECT login_id FROM login WHERE login_username = '" + usuario + "'"
        )
        .getRs()
        .getInt("login_id");
    } catch (EmptyVariableException err) {
      return 0;
    } catch (SQLException err) {
      return -2;
    }
  }

  public String deletar(String usuario) {
    if (!error.equals("")) return error;

    try {
      if (usuario.equals("")) throw new EmptyVariableException(
        "Usuário vazio!"
      );

      return crud.inserirModificarDeletar(
        "DELETE FROM login WHERE login_username = '" + usuario + "'"
      );
    } catch (EmptyVariableException err) {
      return err.getMessage();
    }
  }

  public String sair(String usuario) {
    if (!error.equals("")) return error;

    try {
      if (usuario.equals("")) throw new EmptyVariableException(
        "Usuário vazio!"
      );

      return crud.inserirModificarDeletar(
        "UPDATE login SET login_token = '' WHERE login_username = '" +
        usuario +
        "'"
      );
    } catch (EmptyVariableException err) {
      return err.getMessage();
    }
  }
}
