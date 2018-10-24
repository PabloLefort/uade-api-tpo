package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.UsuarioException;
import negocio.Usuario;

public class UsuarioDAO {
	
	public Usuario buscarUsuarioPorMail(String mailUsuario) throws ConexionException, UsuarioException, AccesoException{  
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;  
		try {    
			con = ConnectionFactory.getInstancia().getConection();
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = "SELECT  * FROM Usuarios where email = '" + mailUsuario + "'";	
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Usuario usuario = new Usuario(rs.getString(1), rs.getString(2), rs.getString(3), null);						
				return usuario;
			}
			else{
				throw new UsuarioException("El mail = " + mailUsuario + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}

	public void insertarUsuario(String nombre, String email, String password, int idRol) throws ConexionException, AccesoException {
		Connection con = null;  
		Statement stmt = null;  
		try {    
			con = ConnectionFactory.getInstancia().getConection();
		}
		catch (ClassNotFoundException | SQLException e) {
			throw new ConexionException("No esta disponible el acceso al Servidor");
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e1) {
			throw new AccesoException("Error de acceso");
		}
		String SQL = "INSERT INTO Usuarios (nombre, email, password, idRol) VALUES (" +
				nombre + ", " + email + ", " + password + ", " + idRol + ")";
		try {
			stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
}
