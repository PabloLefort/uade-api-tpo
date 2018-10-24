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
import negocio.ItemReclamoProducto;
import negocio.Producto;
import negocio.ReclamoProducto;

public class ReclamoProductoDAO {

	private static ReclamoProductoDAO instancia;	
	
	public ReclamoProductoDAO(){};
	
	public static ReclamoProductoDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoProductoDAO();
		}
		return instancia;
	}
	
	public void save(ReclamoProducto reclamo) throws ConexionException, ReclamoException, AccesoException {
	
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
					+ "" + reclamo.getItemReclamoProducto().getProducto().getId() + ", "
					+ "'" + reclamo.getItemReclamoProducto().getCantidad() + "')";
				
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		
	
	}

	public void update(ReclamoProducto reclamo) throws ConexionException, ReclamoException, AccesoException {
		
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

			SQL = "UPDATE ReclamoProducto "					
					+ "SET IdProducto = '" + reclamo.getItemReclamoProducto().getProducto().getId() + "', "
					+ "cantidad = '" + reclamo.getItemReclamoProducto().getCantidad() + "', "
					+ " WHERE Id = '" + reclamo.getId() + "'";
			stmt = con.prepareStatement(SQL);
	        stmt.execute();
	        ConexionPool.closeConexion(con);
	        
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}	
	}

	public ReclamoProducto getById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
		
		String SQL = "SELECT * FROM Reclamo r "
				+ "JOIN ReclamoProducto rp ON r.Id = rp.IdReclamo "
				+ "JOIN Producto p ON rp.IdProducto = p.Id"
				+ "where id = '" + id + "'";

		try {
			
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Cliente cliente = new ClienteDAO().getById(rs.getInt(4));
				Producto producto = new ProductoDAO().obtenerProductoPorId(rs.getInt(7));
				ItemReclamoProducto item = new ItemReclamoProducto(producto, rs.getInt(8));
				
				ReclamoProducto reclamo = new ReclamoProducto(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente, item);
				return reclamo;
				
			}
		} catch (SQLException | ProductoException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}
	
}