package appneo4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class AppNeo4J {

    public static void main(String[] args) {
        // Crea nodos para representar a los amigos
        String codigoAmigo1 = "CREATE (a:Amigo {nombre:'Jose Luis'})";
        String codigoAmigo2 = "CREATE (a:Amigo {nombre:'Nicolas'})";
        
        // Crea relaciones entre los amigos
        String relacionAmigos = "MATCH (a:Amigo {nombre:'Jose Luis'}), (b:Amigo {nombre:'Nicolas'}) "
                              + "CREATE (a)-[:AMIGO_DE]->(b)";

        // Conecta con la base de datos Neo4j
        ConexionNeo4J c = new ConexionNeo4J();
        Connection con = c.conexion();
        
        try {
            // Ejecuta las consultas para crear nodos y relaciones
            PreparedStatement pst1 = con.prepareStatement(codigoAmigo1);
            pst1.executeUpdate();
            
            PreparedStatement pst2 = con.prepareStatement(codigoAmigo2);
            pst2.executeUpdate();
            
            PreparedStatement pst3 = con.prepareStatement(relacionAmigos);
            pst3.executeUpdate();

            JOptionPane.showMessageDialog(null, "Nodos y relaciones creados exitosamente");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}