package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

	public void Save(ReclamoProducto reclamo) throws ConexionException, ReclamoException, AccesoException {
		
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
			
			SQL = "INSERT INTO Producto "
					+ "VALUES ('" + insertId + "', "
					+ "" + reclamo.getItemReclamoProducto().getProducto().getId() + ", "
					+ "'" + reclamo.getItemReclamoProducto().getCantidad() + "')";
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void Update(ReclamoProducto reclamo) throws ConexionException, ReclamoException, AccesoException {
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
			
			SQL = "UPDATE ReclamoProducto "					
					+ "SET IdProducto = '" + reclamo.getItemReclamoProducto().getProducto().getId() + "', "
					+ "cantidad = '" + reclamo.getItemReclamoProducto().getCantidad() + "', "
					+ " WHERE Id = '" + reclamo.getId() + "'";
			
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}		
	}

	public ReclamoProducto GetById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
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
		String SQL = "SELECT * FROM Reclamo r "
				+ "JOIN ReclamoProducto rp ON r.Id = rp.IdReclamo "
				+ "JOIN Producto p ON rp.IdProducto = p.Id"
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
					Producto producto = new ProductoDAO().obtenerProductoPorId(rs.getInt(7));
					ItemReclamoProducto item = new ItemReclamoProducto(producto, rs.getInt(8));
					
					ReclamoProducto reclamo = new ReclamoProducto(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente, item);
					return reclamo;
				}
				catch (ClienteException | ProductoException cx)
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