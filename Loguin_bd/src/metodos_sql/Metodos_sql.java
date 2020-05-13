package metodos_sql;

import java.sql.*;
import java.sql.PreparedStatement;

public class Metodos_sql {

    public static ConexionBD cnx = new ConexionBD();
    public static PreparedStatement sentencia_preparada;
    public static ResultSet resultado;
    public static String sql;
    public static int resultado_numero = 0;

    public int guardar(String nombre, String apellido, String correo, String contraseña) {
        int resultado = 0;
        Connection cnx = null;
        String query = ("INSERT INTO usuarios (nombre,apellido,correo,contraseña) values(?,?,?,?)");

        try {

            cnx = ConexionBD.conectar();
            sentencia_preparada = cnx.prepareStatement(query);

            sentencia_preparada = cnx.prepareStatement(query);
            sentencia_preparada.setString(1, nombre);
            sentencia_preparada.setString(2, apellido);
            sentencia_preparada.setString(3, correo);
            sentencia_preparada.setString(4, contraseña);

            resultado = sentencia_preparada.executeUpdate();

            cnx.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return resultado;
    }

    public static String buscarNombre(String correo) {
        String busquedaNombre = null;
        Connection cnx = null;
        try {
            cnx = ConexionBD.conectar();
            String sentenciaBuscar = ("SELECT nombre, apellido FROM usuarios WHERE correo='" + correo + "'");
            sentencia_preparada = cnx.prepareStatement(sentenciaBuscar);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                busquedaNombre = (nombre + " " + apellido);
            }
            cnx.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return busquedaNombre;
    }

    public static String buscarUsuarioRegistrado(String correo, String contraseña) {
        String busquedaUsuario = null;
        Connection cnx = null;
        try {
            cnx = ConexionBD.conectar();
            String sentenciaBuscarUsuario = ("SELECT * FROM usuarios WHERE correo='" + correo + "' && contraseña ='" + contraseña + "'");
            sentencia_preparada = cnx.prepareStatement(sentenciaBuscarUsuario);
            resultado = sentencia_preparada.executeQuery();
            if (resultado.next()) {
                busquedaUsuario = "usuario encontrado";

            } else {
                busquedaUsuario = "usuario no encontrado";
            }
            cnx.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return busquedaUsuario;
    }
}
