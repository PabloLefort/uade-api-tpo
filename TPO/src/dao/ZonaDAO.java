package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ZonaException;
import excepciones.ConexionException;
import negocio.Zona;

public class ZonaDAO {

	
	public Zona GetById(int id) throws ConexionException, AccesoException, ZonaException	{
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
		String SQL = "SELECT * FROM Zona where id = '" + id + "'";
				
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Zona zona = new Zona(rs.getInt(0), rs.getString(1));
				return zona;
			}
			else{
				throw new ZonaException("El zona con id " + id + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void Update(Zona zona) throws ConexionException, AccesoException, ZonaException	{
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
		String SQL = "UPDATE Zona "
				+ "SET Descripcion = '" + zona.getDescripcion() + "'"
				+ " WHERE Id = '" + zona.getId() + "'";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void Save(Zona zona) throws ConexionException, AccesoException, ZonaException	{
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
		String SQL = "INSERT INTO Zona "
				+ "VALUES ('" + zona.getDescripcion() + "')";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
}
