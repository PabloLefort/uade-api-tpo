package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.AccesoException;
import excepciones.ConexionException;
import excepciones.ReclamoException;
import negocio.Cliente;
import negocio.Reclamo;
import negocio.ReclamoFaltantes;

public class ReclamoFaltantesDAO {

	public Reclamo GetById(int int1) {
		return null;
	}
	
	private static ReclamoFaltantesDAO instancia;	
	
	public ReclamoFaltantesDAO(){};
	
	public static ReclamoFaltantesDAO getInstancia(){
		if(instancia == null){
			instancia = new ReclamoFaltantesDAO();
		}
		return instancia;
	}
	
	
	public void save(ReclamoFaltantes reclamo) {
		
	}
	
	public void update(ReclamoFaltantes reclamo) {
		
	}
	
	public ReclamoFaltantes getById(int id) {
		return null;
	}
}