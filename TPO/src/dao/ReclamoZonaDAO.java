package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import excepciones.ZonaException;
import negocio.Cliente;
import negocio.ReclamoZona;
import negocio.Zona;

public class ReclamoZonaDAO {

	public void Save(ReclamoZona reclamo) throws ConexionException, ReclamoException, AccesoException {
		
		Connection con = null;  
		Statement stmt = null; 
		ResultSet rs = null;
		int insertId = 0;
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
		String SQL = "INSERT INTO Reclamo "
				+ "VALUES ('" + reclamo.getDescripcion() + "', "
				+ "" + reclamo.getEstado().name() + ", "
				+ "'" + reclamo.getFecha() + "', "
				+ "'" + reclamo.getCliente().getId() + "')";
		try {
			stmt.execute(SQL);
			rs = stmt.getGeneratedKeys();
			
			if(rs.next()){
				insertId =rs.getInt(1);
            }
			
			SQL = "INSERT INTO ReclamoZona "
					+ "VALUES ('" + insertId + "', "
					+ "'" + reclamo.getZona().getDescripcion() + "')";
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void Update(ReclamoZona reclamo) throws ConexionException, ReclamoException, AccesoException {
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
		String SQL = "UPDATE Reclamo "
				+ "Descripcion = '" + reclamo.getDescripcion() + "', "
				+ "Estado = '" + reclamo.getEstado().name() + "', "
				+ "Fecha = '" + reclamo.getFecha() + "', "
				+ "IdCliente = '" + reclamo.getCliente().getId() + "'"
				+ " WHERE Id = '" + reclamo.getId() + "'";
		try {
			stmt.execute(SQL);
			
			SQL = "UPDATE ReclamoZona "					
					+ "SET IdZona = '" + reclamo.getZona().getId() + "'"
					+ " WHERE Id = '" + reclamo.getId() + "'";
			
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}		
	}

	public ReclamoZona GetById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
		
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
		String SQL = "SELECT *, z.Descripcion FROM Reclamo r "
				+ "JOIN ReclamoZona rz ON r.Id = rz.IdReclamo "
				+ "JOIN Zona z ON rz.IdZona = z.Id"
				+ "where id = '" + id + "'";
				
		try {
			rs = stmt.executeQuery(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		try {
			if(rs.next()){
				try {
					Cliente cliente = new ClienteDAO().GetById(rs.getInt(4));
					Zona zona = new ZonaDAO().GetById(rs.getInt(4));
					
					ReclamoZona reclamo = new ReclamoZona(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente, zona);
					return reclamo;
				}
				catch (ClienteException | ZonaException cx)
				{
					throw new ClienteException("El cliente con id " + id + " no existe");
				}				
			}
			else{
				throw new ReclamoException("El reclamo con id " + id + " no existe");
			}
		} catch (SQLException e) {
			throw new ConexionException("No es posible acceder a los datos");
		}
	}	
}