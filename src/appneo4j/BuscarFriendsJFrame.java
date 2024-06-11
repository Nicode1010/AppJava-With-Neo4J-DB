/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package appneo4j;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BuscarFriendsJFrame extends javax.swing.JFrame {

    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnListarTodos;
    private javax.swing.JTextArea txtAreaResultados;
    private javax.swing.JTextField txtNombreAmigo;
    
    public BuscarFriendsJFrame() {
        initComponents();
    }
 private void initComponents() {
        txtNombreAmigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        btnListarTodos = new javax.swing.JButton();
        txtAreaResultados = new javax.swing.JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtAreaResultados);
        JLabel lblTitulo = new javax.swing.JLabel("Buscar Amigos");

        setTitle("Buscar Amigos");
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnBuscar.setText("Buscar");
        btnBuscar.setBackground(new java.awt.Color(50, 205, 50));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFocusPainted(false);
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnListarTodos.setText("Listar Todos");
        btnListarTodos.setBackground(new java.awt.Color(70, 130, 180));
        btnListarTodos.setForeground(Color.WHITE);
        btnListarTodos.setFocusPainted(false);
        btnListarTodos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnListarTodosActionPerformed(evt);
            }
        });

        txtAreaResultados.setColumns(20);
        txtAreaResultados.setRows(5);
        txtAreaResultados.setEditable(false);

        BackgroundPanel backgroundPanel = new BackgroundPanel("/resources/fondo.png");

        GroupLayout layout = new GroupLayout(backgroundPanel);
        backgroundPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(lblTitulo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtNombreAmigo)
                        .addComponent(btnBuscar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnListarTodos, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollPane))
                    .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(lblTitulo)
                    .addGap(18, 18, 18)
                    .addComponent(txtNombreAmigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(btnBuscar)
                    .addGap(18, 18, 18)
                    .addComponent(btnListarTodos)
                    .addGap(18, 18, 18)
                    .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                    .addContainerGap(20, Short.MAX_VALUE))
        );

        setContentPane(backgroundPanel);
        pack();
    }

   
    /*
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
  private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {
      String nombreAmigo = txtNombreAmigo.getText();
        String consulta = "MATCH (a:Amigo)-[r:AMIGO_DE]-(b:Amigo) " +
                          "WHERE a.nombre CONTAINS '" + nombreAmigo + "' OR b.nombre CONTAINS '" + nombreAmigo + "' " +
                          "RETURN DISTINCT " +
                          "CASE WHEN a.nombre < b.nombre THEN a.nombre ELSE b.nombre END AS amigo1, " +
                          "CASE WHEN a.nombre < b.nombre THEN b.nombre ELSE a.nombre END AS amigo2";

        ConexionNeo4J c = new ConexionNeo4J();
        Connection con = c.conexion();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            txtAreaResultados.setText("");
            while (rs.next()) {
                txtAreaResultados.append(rs.getString("amigo1") + " es amigo de " + rs.getString("amigo2") + "\n");
            }
        } catch (SQLException e) {
            txtAreaResultados.setText("Error: " + e.getMessage());
        }
    }
  
   private void btnListarTodosActionPerformed(ActionEvent evt) {
        String consulta = "MATCH (a:Amigo) RETURN a.nombre";

        ConexionNeo4J c = new ConexionNeo4J();
        Connection con = c.conexion();

        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(consulta);

            txtAreaResultados.setText("");
            while (rs.next()) {
                txtAreaResultados.append(rs.getString("a.nombre") + "\n");
            }
        } catch (SQLException e) {
            txtAreaResultados.setText("Error: " + e.getMessage());
        }
    }

    public static void main(String args[]) {
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BuscarFriendsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BuscarFriendsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BuscarFriendsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BuscarFriendsJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BuscarFriendsJFrame().setVisible(true);
            }
        });
    }

    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String filePath) {
            try {
                backgroundImage = new ImageIcon(getClass().getResource(filePath)).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

