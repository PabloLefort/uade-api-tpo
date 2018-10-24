package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.Reclamo;
import negocio.ReclamoCompuesto;
import negocio.TiposReclamo;

public class ReclamoCompuestoDAO {

	public void Save(Reclamo reclamo_compuesto, Reclamo reclamo) throws ConexionException, ReclamoException, AccesoException {
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
		try {
			String SQL = "INSERT INTO ReclamoCompuesto "
				+ "VALUES ('" + reclamo_compuesto.getNroReclamo() + "', "
				+ "'" + reclamo_compuesto.getNroReclamo() + "', "
				+ "'" + reclamo.getNroReclamo() + "', "
				+ "'" + reclamo.getTipoReclamo() + "')";
			stmt.execute(SQL);
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}
	
	public ReclamoCompuesto GetById(int id) throws ConexionException, ReclamoException, AccesoException, ClienteException {
		Connection con = null;  
		Statement stmt = null;  
		ResultSet rs = null;
		ReclamoCompuesto reclamo_compuesto = null;
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
		String SQL = "SELECT * FROM ReclamoCompuesto where id = '" + id + "'";
				
		try {
			rs = stmt.executeQuery(SQL);
			if(rs.next()) {
				reclamo_compuesto = (ReclamoCompuesto)getReclamoByTipo(rs.getInt(1), rs.getString(2));
			}
			while(rs != null){
				Reclamo reclamo = getReclamoByTipo(rs.getInt(3), rs.getString(4));
				reclamo_compuesto.add(reclamo);
				rs.next();
			}
			return reclamo_compuesto;
		} catch (SQLException e1) {
			throw new AccesoException("Error de consulta");
		}
	}

	private Reclamo getReclamoByTipo(int id, String tipo) throws ReclamoException {
		Reclamo reclamo = null;
		try {
			if(tipo == TiposReclamo.CANTIDADES.toString()) {
				ReclamoCantidadesDAO rcDao = new ReclamoCantidadesDAO();
				reclamo = rcDao.GetById(id);
			} else if(tipo == TiposReclamo.FACTURACION.toString()) {
				ReclamoFacturacionDAO rcDao = new ReclamoFacturacionDAO();
				reclamo = rcDao.GetById(id);
			} else if(tipo == TiposReclamo.FALTANTES.toString()) {
				ReclamoFaltantesDAO rcDao = new ReclamoFaltantesDAO();
				reclamo = rcDao.GetById(id);
			} else if(tipo == TiposReclamo.PRODUCTO.toString()) {
				ReclamoProductoDAO rcDao = new ReclamoProductoDAO();
				reclamo = rcDao.GetById(id);
			} else if(tipo == TiposReclamo.ZONA.toString()) {
				ReclamoZonaDAO rcDao = new ReclamoZonaDAO();
				reclamo = rcDao.GetById(id);
			}
		} catch (Exception e) {
			throw new ReclamoException("Error de consulta buscando reclamo");
		}
		return reclamo;
	}	

}
