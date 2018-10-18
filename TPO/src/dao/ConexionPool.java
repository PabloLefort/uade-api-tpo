package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPool {

	private Conexion[] conexiones = new Conexion[10];
	private String connectionUrl = "jdbc:sqlserver://pollux.database.windows.net:1433;database=Interactive_Applications;user=TP_2018@pollux;password={yuM;3ZT3};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	private ConexionPool() {
	}

	public static ConexionPool build() throws Throwable {
		ConexionPool retorno = new ConexionPool();
		retorno.init();
		return retorno;
	}

	public void init() throws Throwable {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	}

	public Conexion[] getConections() {
		return conexiones;
	}

	public Connection newConexion() throws SQLException {
		Connection auxiliar = null;
		Integer posicion = null;
		boolean creada = false;
		for (posicion = 0; ((!creada) && (posicion < 10) && (conexiones[posicion] != null)); posicion++) {
			if (conexiones[posicion].getEstatdo() == false) {
				try {
					auxiliar = DriverManager.getConnection(connectionUrl);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conexiones[posicion].setConexion(auxiliar);
				conexiones[posicion].setEstado(true);
				conexiones[posicion].setPosicion(posicion);
				creada = true;
			}
		}
		if (conexiones[posicion] == null) {
			Conexion auxiliar_array = null;
			try {
				auxiliar = DriverManager.getConnection(connectionUrl);
			} catch (Throwable e) {
				e.printStackTrace();
			}
			auxiliar_array = new Conexion();
			auxiliar_array.setConexion(auxiliar);
			auxiliar_array.setEstado(true);
			auxiliar_array.setPosicion(posicion);
			conexiones[posicion] = auxiliar_array;
		}
		return auxiliar;
	}

	public void closeConexion(Connection conexion) {
		boolean encontrado = false;
		for (int posicion = 0; ((!encontrado) && (posicion < 10)); posicion++) {
			if ((conexiones[posicion] != null) && (conexiones[posicion].getConexion() != null)
					&& (conexiones[posicion].getConexion().equals(conexion))) {
				try {
					conexion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				conexiones[posicion].setConexion(null);
				conexiones[posicion].setEstado(false);
				encontrado = true;
			}
		}
	}
}
