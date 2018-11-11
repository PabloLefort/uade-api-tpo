package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.ProductoException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.ItemReclamoCantidad;
import negocio.Producto;
import negocio.ReclamoCantidades;
import services.ConexionPool;

public class ReclamoCantidadesDAO {
	
	private static ReclamoCantidadesDAO instancia;	
	
	public ReclamoCantidadesDAO(){};
	
	public static ReclamoCantidadesDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoCantidadesDAO();
		}
		return instancia;
	}
	
	public void save(ReclamoCantidades reclamo) throws ConexionException, ReclamoException, AccesoException {
		
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
				
			SQL = "INSERT INTO Producto "
						+ "VALUES ('" + insertId + "', "
						+ "" + reclamo.getItemReclamoCantidad().getProducto().getId() + ", "
						+ "'" + reclamo.getItemReclamoCantidad().getCantidad() + "')";
				
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccesoException("Error de consulta");
		}        
	}

	public void update(ReclamoCantidades reclamo) throws ConexionException, ReclamoException, AccesoException {

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
			
			SQL = "UPDATE ReclamoCantidad "					
					+ "SET IdProducto = '" + reclamo.getItemReclamoCantidad().getProducto().getId() + "', "
					+ "cantidad = '" + reclamo.getItemReclamoCantidad().getCantidad() + "', "
					+ " WHERE Id = '" + reclamo.getId() + "'";
			
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}	
	}

	public ReclamoCantidades getById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
		
		String SQL = "SELECT * FROM Reclamo r "
				+ "JOIN ReclamoCantidad rp ON r.Id = rp.IdReclamo "
				+ "JOIN Producto p ON rp.IdProducto = p.Id"
				+ "where id = '" + id + "'";
				
		try {
			
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				
				Cliente cliente = new ClienteDAO().getById(rs.getInt(4));
				Producto producto = new ProductoDAO().obtenerProductoPorId(rs.getInt(7));
				ItemReclamoCantidad item = new ItemReclamoCantidad(producto, rs.getInt(8));
				
				ReclamoCantidades reclamo = new ReclamoCantidades(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente, item);
				return reclamo;
				
			}
		} catch (SQLException | ProductoException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}
	
}