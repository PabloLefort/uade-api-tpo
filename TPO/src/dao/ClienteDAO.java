package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import negocio.Cliente;
import services.ConexionPool;

public class ClienteDAO {
	
	private static ClienteDAO instancia;
	
	
	public ClienteDAO(){};
	
	public static ClienteDAO getInstancia(){
		if(instancia == null){
			instancia = new ClienteDAO();
		}
		return instancia;
	}
	
	public Cliente getById(int id) throws ConexionException, AccesoException, ClienteException	{

		String SQL = "SELECT * FROM Cliente where id = '" + id + "'";
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Cliente cli = new Cliente(rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(4), rs.getInt(2));
				cli.setId(rs.getInt(0));
				return cli;
			}
		
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}
	
	public void update(Cliente cliente) throws ConexionException, AccesoException, ClienteException	{
		
		String SQL = "UPDATE Cliente "
				+ "SET Nombre = '" + cliente.getNombre() + "', "
				+ "Domicilio = '" + cliente.getDomicilio() + "', "
				+ "Email = '" + cliente.getEmail() + "', "
				+ "Telefono = '" + cliente.getTelefono() + "'"
				+ " WHERE Id = '" + cliente.getId() + "'";
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.execute();
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void save(Cliente cliente) throws ConexionException, AccesoException, ClienteException	{
		
		String SQL = "INSERT INTO Cliente "
				+ "VALUES ('" + cliente.getNombre() + "', "
				+ "" + cliente.getDni() + ", "
				+ "'" + cliente.getDomicilio() + "', "
				+ "'" + cliente.getEmail() + "', "
				+ "'" + cliente.getTelefono() + "')";
		
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
