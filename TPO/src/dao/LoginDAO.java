package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import negocio.Usuario;

public class LoginDAO {
    
    public Usuario autenticarUsuario(String email, String password)
            throws ConexionException,  AccesoException, SQLException {
    
        UsuarioDAO     UsuDao = new UsuarioDAO();
        
        String SQL = "SELECT * FROM users "
                + " WHERE email = '" + email + "' AND password = '" + password + "'";
        try {
            
            Connection con = ConexionPool.newConexion();
            PreparedStatement pst = con.prepareStatement(SQL);
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                
                Usuario user;
                try {
                    user = UsuDao.buscarUsuarioPorMail(email);
                    con.close();
                    System.out.println("User authenticated successfully");
                    return user;                    
                    
                } catch (UsuarioException e) {

                    System.out.println(e.getMessage());
                }
            } else {
                
                System.out.println("Invalid username or password!");
            }
            
        }
        catch (SQLException e) {
            throw new AccesoException("Error de consulta");
        }
                
        return null;
    }
}
