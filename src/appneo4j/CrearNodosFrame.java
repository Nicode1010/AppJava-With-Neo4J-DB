package appneo4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CrearNodosFrame extends javax.swing.JFrame {
        private javax.swing.JButton btnCrearAmigo;
    private javax.swing.JTextField txtNombreAmigo;
    private javax.swing.JButton btnCrearRelacion; 
    private javax.swing.JTextField txtNombreSegundoAmigo;
    private javax.swing.JButton btnBuscarAmigos;

    public CrearNodosFrame() {
        initComponents();
    }

    private void initComponents() {
       txtNombreAmigo = new javax.swing.JTextField();
        txtNombreSegundoAmigo = new javax.swing.JTextField();
        btnCrearAmigo = new javax.swing.JButton();
        btnCrearRelacion = new javax.swing.JButton();
        btnBuscarAmigos = new javax.swing.JButton();
        JLabel lblTitulo = new javax.swing.JLabel("SOCIAL FRIENDS");

        setTitle("SOCIAL FRIENDS");
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        FondoPanel fondoPanel = new FondoPanel();

        lblTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 24));
        lblTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtNombreAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreAmigoActionPerformed(evt);
            }
        });

        // Establece colores y estilos para los botones
        btnCrearAmigo.setText("Crear Amigo");
        btnCrearAmigo.setBackground(new java.awt.Color(0, 153, 255));
        btnCrearAmigo.setForeground(Color.WHITE);
        btnCrearAmigo.setFocusPainted(false);
        btnCrearAmigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearAmigoActionPerformed(evt);
            }
        });

        btnCrearRelacion.setText("Crear Relación");
        btnCrearRelacion.setBackground(new java.awt.Color(255, 102, 0));
        btnCrearRelacion.setForeground(Color.WHITE);
        btnCrearRelacion.setFocusPainted(false);
        btnCrearRelacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearRelacionActionPerformed(evt);
            }
        });

        btnBuscarAmigos.setText("Buscar Amigos");
        btnBuscarAmigos.setBackground(new java.awt.Color(50, 205, 50));
        btnBuscarAmigos.setForeground(Color.WHITE);
        btnBuscarAmigos.setFocusPainted(false);
        btnBuscarAmigos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarAmigosActionPerformed(evt);
            }
        });

        // Establece el layout
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(fondoPanel);
        fondoPanel.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreAmigo)
                    .addComponent(btnCrearAmigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNombreSegundoAmigo)
                    .addComponent(btnCrearRelacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBuscarAmigos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(174, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addGap(18, 18, 18)
                .addComponent(txtNombreAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearAmigo)
                .addGap(18, 18, 18)
                .addComponent(txtNombreSegundoAmigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCrearRelacion)
                .addGap(18, 18, 18)
                .addComponent(btnBuscarAmigos)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        setContentPane(fondoPanel);
        pack();
    }

    private void txtNombreAmigoActionPerformed(java.awt.event.ActionEvent evt) {
        // Este método se ejecuta cuando se presiona Enter en el campo de texto.
    }

    private void btnCrearAmigoActionPerformed(java.awt.event.ActionEvent evt) {
        // Método para manejar el evento de clic en el botón "Crear Amigo"
        String nombreAmigo = txtNombreAmigo.getText();
        String codigoCrearAmigo = "CREATE (a:Amigo {nombre:'" + nombreAmigo + "'})";

        ConexionNeo4J c = new ConexionNeo4J();
        Connection con = c.conexion();

        try {
            PreparedStatement pst = con.prepareStatement(codigoCrearAmigo);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Amigo creado exitosamente: " + nombreAmigo);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    

    private void btnCrearRelacionActionPerformed(java.awt.event.ActionEvent evt) {
     // Método para manejar el evento de clic en el botón "Crear Relación"
        String nombreAmigo1 = txtNombreAmigo.getText();
        String nombreAmigo2 = txtNombreSegundoAmigo.getText();
        String relacionAmigos = "MATCH (a:Amigo {nombre:'" + nombreAmigo1 + "'}), (b:Amigo {nombre:'" + nombreAmigo2 + "'}) "
                              + "CREATE (a)-[:AMIGO_DE]->(b)";

        ConexionNeo4J c = new ConexionNeo4J();
        Connection con = c.conexion();

        try {
            PreparedStatement pst = con.prepareStatement(relacionAmigos);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Relación de amigos creada exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    private void btnBuscarAmigosActionPerformed(java.awt.event.ActionEvent evt) {
        new BuscarFriendsJFrame().setVisible(true);
    }
    /*
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setText("MY FRIEND´S GESTIONS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(jLabel1)
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(31, 31, 31))
        );

        jPanel2.setBackground(new java.awt.Color(0, 204, 255));

        jLabel2.setText("DESARROLLADO POR NICOLAS RIOS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(64, 64, 64)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
   try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearNodosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearNodosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearNodosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearNodosFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearNodosFrame().setVisible(true);
            }
        });
    }
     class FondoPanel extends JPanel {
        private Image imagen;

        @Override
        public void paintComponent(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/resources/fondo.jpg")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paintComponent(g);
        }
     }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
