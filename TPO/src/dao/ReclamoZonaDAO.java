package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.ReclamoZona;

public class ReclamoZonaDAO {
	
	public ReclamoZona obtenerReclamoPorId(int idReclamo) throws ConexionException, ReclamoException, AccesoException{  
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
		String SQL = "SELECT  * FROM Reclamo r JOIN ReclamoZona rz ON r.Id = rz.IdReclamo"
				+ " where r.Id = '" + idReclamo + "'";
		
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){		
				Cliente cliente = new Cliente(rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
				
				
				ReclamoZona reclamo = new ReclamoZona(rs.getDate(3), rs.getInt(2), rs.getString(4), cliente);
				return reclamo;
			}
			else{
				throw new ReclamoException("El reclamo id = " + idReclamo + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void insertarReclamoFaltante(ReclamoZona reclamo) throws ConexionException, ReclamoException, AccesoException{  
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
		String SQL = "INSERT INTO Reclamo VALUES ('" + reclamo.getDescripcion() + "','" + reclamo.getFecha() + "','" + reclamo.getCliente().getDni() + "')";
		
		try {
			rs = stmt.executeQuery(SQL);
			int insertId = rs.getInt("id");
			SQL = "INSERT INTO ReclamoZona VALUES ('" + insertId + "','" + reclamo.getZona() + "')";

			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
}