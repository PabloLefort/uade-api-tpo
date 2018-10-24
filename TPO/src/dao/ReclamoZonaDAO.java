package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import excepciones.ZonaException;
import negocio.Cliente;
import negocio.ReclamoZona;
import negocio.Zona;

public class ReclamoZonaDAO {

	private static ReclamoZonaDAO instancia;	
	
	public ReclamoZonaDAO(){};
	
	public static ReclamoZonaDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoZonaDAO();
		}
		return instancia;
	}
	
	public void save(ReclamoZona reclamo) throws ConexionException, ReclamoException, AccesoException {
		
		String SQL = "INSERT INTO Reclamo "
				+ "VALUES ('" + reclamo.getDescripcion() + "', "
				+ "" + reclamo.getEstado().name() + ", "
				+ "'" + reclamo.getFecha() + "', "
				+ "'" + reclamo.getCliente().getId() + "')";
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ResultSet rs = stmt.getGeneratedKeys();
	        int insertId = 0;
	        if (rs.next()) {
	            insertId = rs.getInt(1);
	        }

			SQL = "INSERT INTO ReclamoZona "
					+ "VALUES ('" + insertId + "', "
					+ "'" + reclamo.getZona().getId() + "')";
				
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	}

	public void update(ReclamoZona reclamo) throws ConexionException, ReclamoException, AccesoException {

		String SQL = "UPDATE Reclamo "
				+ "Descripcion = '" + reclamo.getDescripcion() + "', "
				+ "Estado = '" + reclamo.getEstado().name() + "', "
				+ "Fecha = '" + reclamo.getFecha() + "', "
				+ "IdCliente = '" + reclamo.getCliente().getId() + "'"
				+ " WHERE Id = '" + reclamo.getId() + "'";
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
	        stmt.execute();
			
			SQL = "UPDATE ReclamoZona "					
					+ "SET IdZona = '" + reclamo.getZona().getId() + "'"
					+ " WHERE Id = '" + reclamo.getId() + "'";
			
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}	
	}

	public ReclamoZona getById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
		
		String SQL = "SELECT *, z.Descripcion FROM Reclamo r "
				+ "JOIN ReclamoZona rz ON r.Id = rz.IdReclamo "
				+ "JOIN Zona z ON rz.IdZona = z.Id"
				+ "where id = '" + id + "'";
		
		try {
			
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				
				Cliente cliente = new ClienteDAO().getById(rs.getInt(4));
				Zona zona = new ZonaDAO().getById(rs.getInt(4));
				
				ReclamoZona reclamo = new ReclamoZona(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente, zona);
				return reclamo;
				
			}
		} catch (SQLException | ZonaException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}	
}