package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.Factura;
import negocio.ItemFactura;
import negocio.Producto;
import negocio.ReclamoFacturacion;
import services.ConexionPool;

public class ReclamoFacturacionDAO {

	public void save(ReclamoFacturacion reclamo) throws ConexionException, ReclamoException, AccesoException {
		String SQL = "INSERT INTO ReclamoFacturacion "
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

        	for (Iterator iterator = reclamo.getItemsFacturas().iterator(); iterator.hasNext();) {
				ItemFactura aux = (ItemFactura) iterator.next();
				
				SQL = "INSERT INTO ItemFactura "
						+ "VALUES ('" + insertId + "', "
						+ "'" + aux.getCantidad() + ", "
						+ "'" + aux.getProducto().getId() + ", "
						+ "'" + aux.getFactura().getNroFactura() + "')";
				stmt = con.prepareStatement(SQL);
		        stmt.execute();
			}
	        ConexionPool.closeConexion(con);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AccesoException("Error de consulta");
		} 
	}

	public ReclamoFacturacion GetById(int id) throws ConexionException, ReclamoException, AccesoException {
		String SQL = "SELECT * FROM Reclamo r "
				+ "JOIN ReclamoFacturacion rf ON r.Id = rf.IdReclamo "
				+ "WHERE id = '" + id + "'";

		try {
			
			Connection con = ConexionPool.newConexion();
			PreparedStatement stmt = con.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				Cliente cliente = new ClienteDAO().getById(rs.getInt(4));
				ReclamoFacturacion rec = new ReclamoFacturacion(rs.getDate(3), rs.getInt(0), rs.getString(1), cliente);
				
				SQL = "SELECT * FROM Factura f"
					+"JOIN ItemFactura if ON if.facturaId = f.Id"
					+"JOIN Producto p ON p.Id = if.productoId"
					+"WHERE if.reclamoId=" + rec.getId();
				stmt = con.prepareStatement(SQL);
				rs = stmt.executeQuery();
				
				Factura fac = null;
				Producto prod = null;
				ItemFactura itemF = null;
				while(rs.next()) {
					fac = new Factura(rs.getInt(0), rs.getDate(1));
					prod = new Producto(rs.getInt(6), rs.getFloat(7), rs.getString(8), rs.getString(9));
					itemF = new ItemFactura(prod, rs.getInt(2), fac);
					rec.addItemFactura(itemF);
				}

				return rec;
			}
		} catch (SQLException | ClienteException e1) {
			throw new AccesoException("Error de consulta");
		}
		return null;
	}
	
	
	
}