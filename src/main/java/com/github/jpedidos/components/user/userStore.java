/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.jpedidos.components.user;

import com.github.jpedidos.controller.UserController;
import com.github.jpedidos.model.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author Matka
 */
public class userStore extends javax.swing.JPanel {

  /**
   * Creates new form store
   */
  private UserController userControl = new UserController("mydb");

  public userStore() {
    initComponents();
    this.setSize(1800, 635);
    this.setVisible(false);
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
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    txtName = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    txtPrice = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();
    txtName1 = new javax.swing.JTextField();
    txtName2 = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    txtPrice1 = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    txtPrice2 = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();

    jButton2.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          jButton2MouseClicked(evt);
        }
      }
    );

    jButton1.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          jButton1MouseClicked(evt);
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

    jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
    jLabel1.setText("Cadastrar");

    jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel2.setText("Nome:");

    jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel3.setText("E-mail:");

    jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel4.setText("Usuário:");

    jButton1.setText("Salvar");

    jButton2.setText("Limpar");

    jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel5.setText("Cargo:");

    jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel6.setText("Telefone:");

    jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
    jLabel7.setText("Senha:");

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
                .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addGroup(
                  layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.LEADING,
                      false
                    )
                    .addComponent(jLabel6)
                    .addComponent(
                      txtPrice1,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      325,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addGroup(
                      layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING,
                          false
                        )
                        .addComponent(jLabel4)
                        .addComponent(
                          txtPrice,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          325,
                          Short.MAX_VALUE
                        )
                    )
                    .addComponent(jLabel7)
                    .addComponent(
                      txtPrice2,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      325,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addComponent(jLabel1)
                    .addGap(125, 125, 125)
                )
                .addGroup(
                  layout
                    .createSequentialGroup()
                    .addComponent(jButton2)
                    .addGap(18, 18, 18)
                    .addComponent(jButton1)
                )
                .addGroup(
                  layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.LEADING,
                      false
                    )
                    .addComponent(jLabel5)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(
                      txtName,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      325,
                      Short.MAX_VALUE
                    )
                    .addComponent(txtName1)
                    .addComponent(txtName2)
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
            .addGap(200, 200, 200)
            .addComponent(jLabel1)
            .addGap(18, 18, 18)
            .addComponent(jLabel2)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtName,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(jLabel3)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtName1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(18, 18, 18)
            .addComponent(jLabel5)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtName2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(jLabel6)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtPrice1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(jLabel4)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtPrice,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(jLabel7)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              txtPrice2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              33,
              Short.MAX_VALUE
            )
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

  private void jButton2MouseClicked(java.awt.event.MouseEvent evt) {
    limparTextFields();
  }

  private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {
    String result = userControl.cadastrar(txtName.getText(), txtName1.getText(), txtName2.getText(), Integer.parseInt(txtPrice1.getText()), txtPrice.getText(), txtPrice2.getText());
    JOptionPane.showMessageDialog(
        null,
        result,
        "Aviso",
        JOptionPane.INFORMATION_MESSAGE
      );
    limparTextFields();
  }

  private void limparTextFields() {
    txtName.setText("");
    txtName1.setText("");
    txtName2.setText("");
    txtPrice.setText("");
    txtPrice1.setText("");
    txtPrice2.setText("");
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JTextField txtName;
  private javax.swing.JTextField txtName1;
  private javax.swing.JTextField txtName2;
  private javax.swing.JTextField txtPrice;
  private javax.swing.JTextField txtPrice1;
  private javax.swing.JTextField txtPrice2;
  // End of variables declaration//GEN-END:variables
}
