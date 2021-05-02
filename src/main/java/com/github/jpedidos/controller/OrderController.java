package com.github.jpedidos.controller;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;

public class OrderController {

  CRUD crud = new CRUD();
  Connection connection;
  String result;

  public String cadastrarPedido(int id) {
    result =
      crud.inserirModificarDeletar(
        "INSERT INTO client_order (order_status, order_value ,user_user_id) " +
        "VALUES(1, 0, " +
        id +
        ")"
      );

    return result;
  }

  public void fecharPedido(int id) {
    crud.inserirModificarDeletar("UPDATE client_order SET order_status = 2 WHERE order_id = " + id);
  }

  public String adicionarProduto(
    String produto,
    int quantidade,
    int id,
    int user_id
  ) {
    try {
      produto.length();
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
    } catch (Exception e) {
      System.out.println(e);
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
    try {
      produto.length();
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
    } catch (Exception e) {
      System.out.println(e);
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
    return crud.buscar("Select * FROM client_order WHERE order_id =" + id);
  }

  public int ultimoId(String teste) {
    try {
      teste.length();
      connection = crud.buscar("Select MAX(order_id) FROM client_order");

      return connection
        .getRs()
        .getInt("MAX(order_id)");
    } catch (Exception e) {
      System.out.println(e);
      return 0;
    }
  }

  public String deletar(int id, String teste) {
    try {
      teste.length();
      crud.inserirModificarDeletar(
        "DELETE FROM order_item WHERE client_order_order_id = '" + id + "'"
      );

      return crud.inserirModificarDeletar(
        "DELETE FROM client_order WHERE order_id = '" + id + "'"
      );
    } catch (Exception e) {
      System.out.println(e);
      return "Produto não existe!";
    }
  }
}
