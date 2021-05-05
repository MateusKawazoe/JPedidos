package com.github.jpedidos.controller;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import com.github.jpedidos.validate.*;
import java.sql.SQLException;

public class OrderController {

  Connection connection;
  String result, error = "";
  CRUD crud;

  public OrderController(String bd) {
    try {
      if (
        bd.equals("") || !bd.equals("teste") && !bd.equals("mydb")
      ) throw new InvalidConnextionException("Nome do banco de dados inválido");
      crud = new CRUD(bd);
    } catch (InvalidConnextionException err) {
      error = err.getMessage();
    }
  }

  public String cadastrarPedido(int id) {
    if (!error.equals("")) return error;

    try {
      if (id == 0) throw new EmptyVariableException("Id inválido!");

      result =
        crud.inserirModificarDeletar(
          "INSERT INTO client_order (order_status, order_value, order_open, user_user_id) " +
          "VALUES(1, 0, CURRENT_TIMESTAMP, " +
          id +
          ")"
        );
    } catch (Exception e) {
      return e.getMessage();
    }

    return result;
  }

  public String fecharPedido(int id) {
    if (!error.equals("")) return error;

    try {
      if (id == 0) throw new EmptyVariableException("Id inválido!");
      return crud.inserirModificarDeletar(
        "UPDATE client_order SET order_status = 2, order_close = CURRENT_TIMESTAMP WHERE order_id = " + id
      );
    } catch (EmptyVariableException e) {
      return e.getMessage();
    }
  }

  public String adicionarProduto(
    String produto,
    int quantidade,
    int id,
    int user_id
  ) {
    if (!error.equals("")) return error;

    try {
      if (
        produto.equals("") || quantidade < 1 || id < 1 || user_id < 1
      ) throw new EmptyVariableException("Existem campos vazios!");

      int aux = quantidade, product_id;
      connection =
        crud.buscar(
          "SELECT product_id FROM product WHERE product_name = '" +
          produto +
          "'"
        );

      if (connection != null) {
        product_id = connection.getRs().getInt("product_id");

        connection =
          crud.buscar(
            "SELECT order_item_qtd FROM order_item WHERE product_product_id =" +
            product_id +
            " AND client_order_order_id =" +
            id
          );

        if (connection != null) {
          aux = connection.getRs().getInt("order_item_qtd");

          aux += quantidade;

          crud.inserirModificarDeletar(
            "UPDATE order_item SET order_item_qtd =" +
            aux +
            " WHERE product_product_id =" +
            product_id +
            " AND client_order_order_id =" +
            id
          );
        } else {
          crud.inserirModificarDeletar(
            "INSERT INTO order_item (order_item_qtd, product_product_id, client_order_order_id) " +
            "VALUES(" +
            aux +
            "," +
            product_id +
            ", " +
            id +
            ")"
          );
        }
      } else {
        return "Produto não existe!";
      }
    } catch (EmptyVariableException e) {
      return e.getMessage();
    } catch (SQLException e) {
      return e.getMessage();
    }
    result =
      crud.inserirModificarDeletar(
        "UPDATE client_order c JOIN (SELECT max(order_id) AS idMax, order_value AS oValue from client_order " +
        "c2 WHERE user_user_id = " +
        user_id +
        ") AS aux on order_id = aux.idMax SET  c.order_value = (SELECT " +
        "product_price FROM product WHERE product_name = '" +
        produto +
        "') * " +
        quantidade +
        " + aux.oValue"
      );

    return result;
  }

  public String removerProduto(
    String produto,
    int quantidade,
    int id,
    int user_id
  ) {
    if (!error.equals("")) return error;

    try {
      if (
        produto.equals("") || quantidade < 1 || id < 1 || user_id < 1
      ) throw new EmptyVariableException("Existem campos vazios!");
      int aux = quantidade, product_id;
      connection =
        crud.buscar(
          "SELECT product_id FROM product WHERE product_name = '" +
          produto +
          "'"
        );

      if (connection != null) {
        product_id = connection.getRs().getInt("product_id");

        connection =
          crud.buscar(
            "SELECT order_item_qtd FROM order_item WHERE product_product_id =" +
            product_id +
            " AND client_order_order_id =" +
            id
          );

        if (connection != null) {
          aux = connection.getRs().getInt("order_item_qtd");
          connection.close();
        }

        aux = aux - quantidade;

        if (aux < 0) {
          return "Operação inválida!";
        } else if (aux == 0) {
          crud.inserirModificarDeletar(
            "UPDATE client_order SET order_value = 0 WHERE user_user_id = " +
            user_id
          );
          result =
            crud.inserirModificarDeletar(
              "DELETE FROM order_item WHERE product_product_id =" +
              product_id +
              " AND client_order_order_id =" +
              id
            );
          return result;
        }

        crud.inserirModificarDeletar(
          "UPDATE order_item SET order_item_qtd =" +
          aux +
          " WHERE product_product_id=" +
          product_id +
          " AND client_order_order_id =" +
          id
        );
      } else {
        return "Produto não existe!";
      }
    } catch (EmptyVariableException e) {
      return e.getMessage();
    } catch (SQLException e) {
      return e.getMessage();
    }
    result =
      crud.inserirModificarDeletar(
        "UPDATE client_order c JOIN (SELECT max(order_id) AS idMax, order_value AS oValue from client_order " +
        "c2 WHERE user_user_id = " +
        user_id +
        ") AS aux on order_id = aux.idMax SET  c.order_value = aux.oValue - (SELECT " +
        "product_price FROM product WHERE product_name = '" +
        produto +
        "') * " +
        quantidade
      );

    return result;
  }

  public Connection listar(int id) {
    if (!error.equals("")) return null;

    if (id == 0) {
      return crud.buscar(
        "Select * FROM client_order NATURAL JOIN order_item NATURAL JOIN product"
      );
    } else {
      return crud.buscar(
        "Select order_value, order_item_qtd, product_name, product_description FROM client_order " +
        " NATURAL JOIN order_item NATURAL JOIN product WHERE user_user_id = '" +
        id +
        "'"
      );
    }
  }

  public Connection buscarUmPedido(int id) {
    if (!error.equals("")) return null;

    return crud.buscar("Select * FROM client_order WHERE order_id =" + id);
  }

  public int ultimoId() {
    if (!error.equals("")) return 0;

    try {
      connection = crud.buscar("Select MAX(order_id) FROM client_order");
      return connection.getRs().getInt("MAX(order_id)");
    } catch (SQLException e) {
      return -1;
    }
  }

  public String deletar(int id) {
    if (!error.equals("")) return error;

    try {
      if(id < 1) throw new EmptyVariableException("Id inválido!");

      crud.inserirModificarDeletar(
        "DELETE FROM order_item WHERE client_order_order_id = '" + id + "'"
      );

      return crud.inserirModificarDeletar(
        "DELETE FROM client_order WHERE order_id = '" + id + "'"
      );
    } catch (EmptyVariableException e) {
      return e.getMessage();
    }
  }
}
