package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.ZonaException;
import negocio.Zona;
import services.ConexionPool;

public class ZonaDAO {

	private static ZonaDAO instancia;
	
	
	public ZonaDAO(){};
	
	public static ZonaDAO getInstancia(){
		if(instancia == null){
			instancia = new ZonaDAO();
		}
		return instancia;
	}
	
	
	public Zona getById(int id) throws ConexionException, AccesoException, ZonaException	{
		
		String SQL = "SELECT * FROM Zona where id = '" + id + "'";

		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Zona zona = new Zona(rs.getInt(0), rs.getString(1));
				return zona;
			}
		
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}
	
	public void update(Zona zona) throws ConexionException, AccesoException, ZonaException	{
		
		String SQL = "UPDATE Zona "
				+ "SET Descripcion = '" + zona.getDescripcion() + "'"
				+ " WHERE Id = '" + zona.getId() + "'";
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.execute();
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void save(Zona zona) throws ConexionException, AccesoException, ZonaException	{

		String SQL = "INSERT INTO Zona "
				+ "VALUES ('" + zona.getDescripcion() + "')";
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}
}
