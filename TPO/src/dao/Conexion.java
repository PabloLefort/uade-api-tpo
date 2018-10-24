package dao;

import java.sql.Connection;

public class Conexion {
	
	private Connection conexion = null;
	private Boolean estado = Boolean.FALSE;
	private Integer posicion = null;
	
	public Conexion() {
	}
	
	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estato) {
		this.estado = estato;
	}

	public Integer getPosicion() {
		return posicion;
	}

	public void setPosicion(Integer posicion) {
		this.posicion = posicion;
	}

}
