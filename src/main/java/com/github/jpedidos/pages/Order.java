package com.github.jpedidos.pages;

import com.github.jpedidos.controller.OrderController;
import com.github.jpedidos.controller.ProductController;
import com.github.jpedidos.model.Connection;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;

public class Order {

  Connection connection;
  JFrame login = new JFrame();
  JOptionPane optionPane = new JOptionPane();
  DecimalFormat df = new DecimalFormat("0.00");
  OrderController orderController = new OrderController();
  ProductController productController = new ProductController();
  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

  JPanel container = new JPanel(new FlowLayout());
  JPanel p_titulo = new JPanel();
  JPanel p_cliente = new JPanel(new BorderLayout(10, 0));
  JPanel p_produto = new JPanel(new BorderLayout(10, 0));
  JPanel p_quantidade = new JPanel(new BorderLayout(10, 0));
  JPanel p_botoes = new JPanel();

  JLabel lb_cliente = new JLabel("Cliente", JLabel.LEFT);
  JLabel lb_produto = new JLabel("Produto", JLabel.LEFT);
  JLabel lb_quantidade = new JLabel("Quantidade", JLabel.LEFT);
  JLabel h1 = new JLabel("JPedidos", JLabel.CENTER);

  JTextField tx_cliente = new JTextField(20);
  JTextField tx_produto = new JTextField(20);
  JTextField tx_quantidade = new JTextField(20);

  JButton b_entrar = new JButton("Realizer Pedido");

  public Order() {
    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    login.setTitle("Cadastrar produto");
    login.setSize(400, 450);
    login.setLocation(
      dim.width / 2 - login.getSize().width / 2,
      dim.height / 2 - login.getSize().height / 2
    );
    p_titulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_cliente.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_produto.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_quantidade.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_botoes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    container.add(p_titulo);
    container.add(p_cliente);
    container.add(p_produto);
    container.add(p_quantidade);
    container.add(p_botoes);

    h1.setFont(h1.getFont().deriveFont(50.0f));
    p_titulo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
    p_titulo.add(h1, BorderLayout.CENTER);

    tx_cliente.setFont(tx_cliente.getFont().deriveFont(18.0f));
    tx_cliente.setSize(300, 60);
    p_cliente.add(lb_cliente, BorderLayout.NORTH);
    p_cliente.add(tx_cliente, BorderLayout.CENTER);

    tx_produto.setFont(tx_produto.getFont().deriveFont(18.0f));
    tx_produto.setSize(300, 60);
    p_produto.add(lb_produto, BorderLayout.NORTH);
    p_produto.add(tx_produto, BorderLayout.CENTER);

    tx_quantidade.setFont(tx_quantidade.getFont().deriveFont(18.0f));
    tx_quantidade.setSize(300, 60);
    p_quantidade.add(lb_quantidade, BorderLayout.NORTH);
    p_quantidade.add(tx_quantidade, BorderLayout.CENTER);

    b_entrar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          int product_id = productController.idProduto(tx_produto.getText());

          String result = orderController.cadastrarPedido(2);

          if (!result.equals("false")) {
            JOptionPane.showMessageDialog(
              null,
              "Aviso",
              "Erro ao cadastrar pedido!",
              JOptionPane.ERROR_MESSAGE
            );
          } else {
            int order_id = orderController.ultimoId("Loucura");
            int quantidade = Integer.parseInt(tx_quantidade.getText());
            System.out.println(order_id + "\n" + product_id);
            if (product_id != 0) {
              result =
                orderController.adicionarProduto(
                  tx_produto.getText(),
                  quantidade,
                  order_id,
                  2
                );
              try {
                connection = orderController.buscarUmPedido(order_id);

                JOptionPane.showMessageDialog(
                  null,
                  "Cliente: " +
                  tx_cliente.getText() +
                  "\n" +
                  "Produto: " +
                  tx_produto.getText() +
                  "\n" +
                  "Valor unitário: " +
                  32.99 +
                  "\n" +
                  "Quantidade: " +
                  tx_quantidade.getText() +
                  "\n" +
                  "Valor total: " +
                  "\n" +
                  "Estado: Aberto" +
                  "\n" +
                  df.format(connection.getRs().getFloat("order_value")),
                  "Informações do pedido",
                  JOptionPane.INFORMATION_MESSAGE
                );
                connection.close();
              } catch (Exception error) {
                System.out.println(error);
              }
            } else {
              JOptionPane.showMessageDialog(
                null,
                "Aviso",
                "Produto não existe!",
                JOptionPane.ERROR_MESSAGE
              );
            }
          }
        }
      }
    );

    p_botoes.setLayout(new FlowLayout(FlowLayout.CENTER, 126, 30));
    p_botoes.add(b_entrar);

    login.setContentPane(container);
    login.setVisible(true);
  }
}
