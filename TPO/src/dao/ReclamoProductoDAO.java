package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.ReclamoProducto;

public class ReclamoProductoDAO {
	
	public ReclamoProducto obtenerReclamoPorId(int idReclamo) throws ConexionException, ReclamoException, AccesoException{  
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
		String SQL  = "SELECT  * FROM Reclamo r JOIN ReclamoProducto rp ON r.Id = rp.IdReclamo"
				+ " where r.Id = '" + idReclamo + "'";

		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){			
				Cliente cliente = new Cliente(rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getInt(10));
				
				ReclamoProducto reclamo = new ReclamoProducto(rs.getDate(3), rs.getInt(2), rs.getString(4), cliente);
								
				String SQLItem = "SELECT * FROM ItemReclamoProducto where Id = '" + reclamo.getNroReclamo() + "'";
				
				try {
					rs = stmt.executeQuery(SQLItem);
				} catch (SQLException e1) {
					throw new AccesoException("Error de consulta");
				}
				//Agrego los productos relacionados al reclamo.
				while(rs.next()) 
				{					
					//reclamo.addProducto(new Producto(rs.getInt(2)), rs.getInt(3));
				}
				
				return reclamo;
			}
			else{
				throw new ReclamoException("El reclamo id = " + idReclamo + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}
	
	public void insertReclamo(ReclamoProducto reclamo) throws ConexionException, ReclamoException, AccesoException{  
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
			SQL = "INSERT INTO ReclamoProducto VALUES ('" + insertId + "','" + reclamo.getProducto().getCodigo() + "','" + 1 + "')";
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
}