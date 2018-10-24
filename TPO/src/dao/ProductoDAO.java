package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.ProductoException;
import negocio.Producto;

public class ProductoDAO {

	private static ProductoDAO instancia;
	
	
	public ProductoDAO(){};
	
	public static ProductoDAO getInstancia(){
		if(instancia == null){
			instancia = new ProductoDAO();
		}
		return instancia;
	}
	
	public Producto obtenerProductoPorId(int numero) throws ConexionException, AccesoException, ProductoException{  
		String SQL = "SELECT * FROM Producto where id = '" + numero;
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Producto prod = new Producto(numero, rs.getFloat(2), rs.getString(3), rs.getString(5));
				return prod;
			}
		
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}

	public void eliminarProductoPorId(int codProducto) throws ConexionException, AccesoException {
		
		String SQL = "DELETE FROM Producto where id = '" + codProducto;
			
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.execute();
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	public void insertarProducto(int codProducto, float precio, String nombre, String descripcion) throws ConexionException, AccesoException {

		String SQL = "INSERT INTO Producto (codProducto, precio, nombre, descripcion) " +
				"VALUES (" + codProducto + ", " + precio + ", " + nombre + "," + descripcion + ")";
		
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

	public void modificarProducto(int codProducto, float precio, String nombre, String descripcion) throws ConexionException, AccesoException {

		String SQL = "UPDATE Producto SET precio = " + precio + ", nombre = " + nombre + ", descripcion = " + descripcion + 
				"WHERE codProducto = " + codProducto;
		
		try {
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			stmt.execute();
			
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
	
	
}
