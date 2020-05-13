package metodos_sql;

import javax.swing.JOptionPane;
import java.sql.*;

/**
 *
 * @author Usuario
 */
public class ConexionBD {

    static Connection cnx = null;
    
   // public static void main(String[] args) {
        
    
    public static Connection conectar() {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            cnx = DriverManager.getConnection("jdbc:Mysql://localhost/login_bd?user=root&password=");
            //JOptionPane.showMessageDialog(null, "Conectado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error al conectar");
        }
        return cnx;
    }

}
