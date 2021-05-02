package com.github.jpedidos.controller;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;

public class ProductController {

  CRUD crud = new CRUD();
  Connection connection;
  String result;

  public String cadastrar(String nome, String descricao, Float preco) {
    try {
      nome.length();
      connection =
        crud.buscar(
          "SELECT * FROM product WHERE product_name = '" + nome + "'"
        );
      if (connection != null) {
        connection.close();
        return "Produto já cadastrado!";
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    if (nome != null) {
      result =
        crud.inserirModificarDeletar(
          "INSERT INTO product (product_name, product_description, product_price) " +
          "VALUES('" +
          nome +
          "', '" +
          descricao +
          "', " +
          preco +
          ")"
        );
    }

    return "Produto cadastrado com sucesso!";
  }

  public String alterar(String nome, String descricao, Float preco) {
    String auxDescricao = descricao;
    Float auxPreco = preco;

    try {
      nome.length();
      connection =
        crud.buscar(
          "SELECT product_description, product_price FROM product WHERE product_name = '" +
          nome +
          "'"
        );

      if (auxDescricao.equals("")) auxDescricao =
        connection.getRs().getString("product_description");
      if (auxPreco == 0) auxPreco =
        connection.getRs().getFloat("product_price");
    } catch (Exception e) {
      System.out.println(e);
    }

    result =
      crud.inserirModificarDeletar(
        "UPDATE product SET product_description = '" +
        auxDescricao +
        "', product_price =" +
        auxPreco +
        " " +
        "WHERE product_name = '" +
        nome +
        "'"
      );

    if (result.equals("false")) {
      return "Produto alterado com sucesso!";
    }

    return result;
  }

  public Connection listar(String nome) {
    if (nome.equals("")) {
      return crud.buscar("Select * FROM product");
    } else {
      return crud.buscar(
        "Select * FROM product WHERE product_name = '" + nome + "'"
      );
    }
  }

  public Connection buscarProduto(int id) {
    return crud.buscar("Select * FROM product WHERE product_id = " + id);
  }

  public int idProduto(String nome) {
    try {
      nome.length();
      connection =
        crud.buscar(
          "Select product_id FROM product WHERE product_name ='" + nome + "'"
        );

      if (connection != null) return connection.getRs().getInt("product_id");
    } catch (Exception e) {
      System.out.println(e);
    }
    return 0;
  }

  public String deletar(String nome) {
    try {
      nome.length();
      return crud.inserirModificarDeletar(
        "DELETE FROM product WHERE product_name = '" + nome + "'"
      );
    } catch (Exception e) {
      System.out.println(e);
    }

    return "Produto não existe!";
  }
}
