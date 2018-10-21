package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import negocio.Cliente;

public class ClienteDAO {
	
	public Cliente GetById(int id) throws ConexionException, AccesoException, ClienteException	{
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
		String SQL = "SELECT * FROM Cliente where id = '" + id + "'";
				
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				Cliente cli = new Cliente(rs.getString(1), rs.getString(3), rs.getString(5), rs.getString(4), rs.getInt(2));
				cli.setId(rs.getInt(0));
				return cli;
			}
			else{
				throw new ClienteException("El cliente con id " + id + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void Update(Cliente cliente) throws ConexionException, AccesoException, ClienteException	{
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
		String SQL = "UPDATE Cliente "
				+ "SET Nombre = '" + cliente.getNombre() + "', "
				+ "Domicilio = '" + cliente.getDomicilio() + "', "
				+ "Email = '" + cliente.getEmail() + "', "
				+ "Telefono = '" + cliente.getTelefono() + "'"
				+ " WHERE Id = '" + cliente.getId() + "'";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void Save(Cliente cliente) throws ConexionException, AccesoException, ClienteException	{
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
		String SQL = "INSERT INTO Cliente "
				+ "VALUES ('" + cliente.getNombre() + "', "
				+ "" + cliente.getDni() + ", "
				+ "'" + cliente.getDomicilio() + "', "
				+ "'" + cliente.getEmail() + "', "
				+ "'" + cliente.getTelefono() + "')";
		try {
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
}
