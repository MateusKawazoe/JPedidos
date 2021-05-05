/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jpedidos.components.product;

import com.github.jpedidos.controller.ProductController;
import com.github.jpedidos.model.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Matka
 */
public class productRUD extends javax.swing.JPanel {

  private ProductController pdController = new ProductController("mydb");
  private List<String> produtos = new ArrayList<String>();
  private Connection con = null;
  private String[] aux;

  /**
   * Creates new form store
   */
  public productRUD() {
    try {
      con = pdController.listar("");

      if (con != null) {
        produtos.add(con.getRs().getString("product_name"));

        while (con.getRs().next()) {
          produtos.add(con.getRs().getString("product_name"));
        }
        con.close();
      }
      aux = new String[produtos.size()];
      aux = produtos.toArray(aux);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(
        null,
        e.getMessage(),
        "Erro",
        JOptionPane.ERROR_MESSAGE
      );
    }
    initComponents();
    this.setSize(1800, 591);
    this.setVisible(true);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    txtPrice = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jScrollPane1 = new javax.swing.JScrollPane();
    txtDescription = new javax.swing.JTextArea();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    jButton3 = new javax.swing.JButton();
    jComboBox1 = new javax.swing.JComboBox<>();

    jButton1.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          bDeletar(evt);
        }
      }
    );

    jButton2.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          bEditar(evt);
        }
      }
    );

    jButton3.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          bBuscar(evt);
        }
      }
    );

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
      jPanel1
    );
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
      jPanel2
    );
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 100, Short.MAX_VALUE)
    );

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Nome:");

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("Descrição:");

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel4.setText("Preço:");
    txtPrice.setText("0.00");

    txtDescription.setColumns(20);
    txtDescription.setRows(5);
    jScrollPane1.setViewportView(txtDescription);

    jButton1.setText("Deletar");

    jButton2.setText("Editar");

    jButton3.setText("Buscar");

    jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(aux));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap(30, Short.MAX_VALUE)
            .addGroup(
              layout
                .createParallelGroup(
                  javax.swing.GroupLayout.Alignment.TRAILING,
                  false
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addComponent(jButton2)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                )
                .addComponent(
                  jLabel4,
                  javax.swing.GroupLayout.Alignment.LEADING
                )
                .addComponent(
                  txtPrice,
                  javax.swing.GroupLayout.Alignment.LEADING,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  325,
                  Short.MAX_VALUE
                )
                .addComponent(
                  jLabel3,
                  javax.swing.GroupLayout.Alignment.LEADING
                )
                .addComponent(
                  jLabel2,
                  javax.swing.GroupLayout.Alignment.LEADING
                )
                .addComponent(
                  jScrollPane1,
                  javax.swing.GroupLayout.Alignment.LEADING
                )
                .addComponent(jButton3)
                .addComponent(
                  jComboBox1,
                  javax.swing.GroupLayout.Alignment.LEADING,
                  0,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
            )
            .addContainerGap(30, Short.MAX_VALUE)
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(240, 240, 240)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jComboBox1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(jButton3)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              11,
              Short.MAX_VALUE
            )
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jScrollPane1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtPrice,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(34, 34, 34)
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jButton1)
                .addComponent(jButton2)
            )
            .addGap(19, 19, 19)
        )
    );
  } // </editor-fold>//GEN-END:initComponents

  private void bBuscar(java.awt.event.MouseEvent evt) {
    try {
      con = pdController.listar(jComboBox1.getSelectedItem() + "");

      if (con.getRs() != null) {
        txtDescription.setText(con.getRs().getString("product_description"));
        txtPrice.setText(con.getRs().getString("product_price"));
        con.close();
      }
    } catch (Exception e) {
      //TODO: handle exception
    }
  }

  private void bDeletar(java.awt.event.MouseEvent evt) {
    if (jComboBox1.getSelectedIndex() > -1) {
      if (
        pdController.deletar(jComboBox1.getSelectedItem() + "").equals("false")
      ) {
        JOptionPane.showMessageDialog(
          null,
          "Produto excluído com sucesso!",
          "Aviso",
          JOptionPane.INFORMATION_MESSAGE
        );
        jComboBox1.removeItem(jComboBox1.getSelectedItem());
        txtDescription.setText("");
        txtPrice.setText("0.00");
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Produto não pode ser deletado!",
          "Erro",
          JOptionPane.ERROR_MESSAGE
        );
      }
    }
  }

  private void bEditar(java.awt.event.MouseEvent evt) {
    if (jComboBox1.getSelectedIndex() > -1) {
      JOptionPane.showMessageDialog(
        null,
        pdController.alterar(jComboBox1.getSelectedItem() + "", txtDescription.getText(), Float.parseFloat(txtPrice.getText())),
        "Aviso",
        JOptionPane.INFORMATION_MESSAGE
      );
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Produto não pode ser alterado!",
        "Erro",
        JOptionPane.ERROR_MESSAGE
      );
    }
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JButton jButton3;
  private javax.swing.JComboBox<String> jComboBox1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTextArea txtDescription;
  private javax.swing.JTextField txtPrice;
  // End of variables declaration//GEN-END:variables
}
