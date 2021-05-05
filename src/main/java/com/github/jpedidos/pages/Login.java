package com.github.jpedidos.pages;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.github.jpedidos.controller.LoginController;

public class Login {

  JFrame login = new JFrame();
  JOptionPane optionPane = new JOptionPane();
  LoginController controller = new LoginController("mydb");
  Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

  JPanel container = new JPanel(new FlowLayout());
  JPanel p_titulo = new JPanel();
  JPanel p_usuario = new JPanel(new BorderLayout(10, 0));
  JPanel p_senha = new JPanel(new BorderLayout(10, 0));
  JPanel p_botoes = new JPanel();

  JLabel lb_usuario = new JLabel("Usuário", JLabel.LEFT);
  JLabel lb_senha = new JLabel("Senha", JLabel.LEFT);
  JLabel h1 = new JLabel("JPedidos", JLabel.CENTER);

  JTextField tx_usuario = new JTextField(20);
  JPasswordField tx_senha = new JPasswordField(20);

  JButton b_entrar = new JButton("Entrar");
  JButton b_cadastrar = new JButton("Cadastrar");

  public Login() {
    login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    login.setTitle("Login");
    login.setSize(400, 400);
    login.setLocation(
      dim.width / 2 - login.getSize().width / 2,
      dim.height / 2 - login.getSize().height / 2
    );
    p_titulo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_usuario.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_senha.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    p_botoes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    container.add(p_titulo);
    container.add(p_usuario);
    container.add(p_senha);
    container.add(p_botoes);

    h1.setFont(h1.getFont().deriveFont(50.0f));
    p_titulo.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
    p_titulo.add(h1, BorderLayout.CENTER);

    tx_usuario.setFont(tx_usuario.getFont().deriveFont(18.0f));
    tx_usuario.setSize(300, 60);
    p_usuario.add(lb_usuario, BorderLayout.NORTH);
    p_usuario.add(tx_usuario, BorderLayout.CENTER);

    tx_senha.setFont(tx_senha.getFont().deriveFont(18.0f));
    tx_senha.setSize(300, 60);
    p_senha.add(lb_senha, BorderLayout.NORTH);
    p_senha.add(tx_senha, BorderLayout.CENTER);

    b_entrar.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String result = controller.entrar(tx_usuario.getText(), new String(tx_senha.getPassword()));

          if(result.equals("Login realizado com sucesso!")) {
            JOptionPane.showMessageDialog(null, result, "Aviso", JOptionPane.INFORMATION_MESSAGE);
            new Home(tx_usuario.getText());
            login.dispose();
          } else {
            JOptionPane.showMessageDialog(null, result, "Aviso", JOptionPane.ERROR_MESSAGE);
          }
        }
      }
    );

    p_botoes.setLayout(new FlowLayout(FlowLayout.CENTER, 126, 30));
    // p_botoes.add(b_cadastrar);
    p_botoes.add(b_entrar);

    login.setContentPane(container);
    login.setVisible(true);
  }
}
