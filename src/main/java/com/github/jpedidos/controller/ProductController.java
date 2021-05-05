package com.github.jpedidos.controller;

import com.github.jpedidos.model.CRUD;
import com.github.jpedidos.model.Connection;
import com.github.jpedidos.validate.InvalidConnextionException;

public class ProductController {

  Connection connection;
  String result, error = "";
  CRUD crud;

  public ProductController(String bd) {
    try {
      if (
        bd.equals("") || !bd.equals("teste") && !bd.equals("mydb")
      ) throw new InvalidConnextionException("Nome do banco de dados inválido");
      crud = new CRUD(bd);
    } catch (InvalidConnextionException e) {
      error = e.getMessage();
    }
  }

  public String cadastrar(String nome, String descricao, Float preco) {
    if (!error.equals("")) return error;
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
    if (!error.equals("")) return error;
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
    if (!error.equals("")) return null;
    if (nome.equals("")) {
      return crud.buscar("Select * FROM product");
    } else {
      return crud.buscar(
        "Select * FROM product WHERE product_name = '" + nome + "'"
      );
    }
  }

  public Connection buscarProduto(int id) {
    if (!error.equals("")) return null;
    return crud.buscar("Select * FROM product WHERE product_id = " + id);
  }

  public int idProduto(String nome) {
    if (!error.equals("")) return 0;
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
    return -1;
  }

  public String deletar(String nome) {
    if (!error.equals("")) return error;

    return crud.inserirModificarDeletar(
      "DELETE FROM product WHERE product_name = '" + nome + "'"
    );
  }
}
