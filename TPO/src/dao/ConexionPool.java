package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionPool {
	
	private static Boolean INITIALIZED = Boolean.FALSE;
	private static Boolean DEBUG = Boolean.TRUE;
	private static final int POOL_SIZE = 5;
	private static int ULTIMA_POSICION_LIBRE = 0;
	private static boolean RECURSIVIDAD = false;
	private static Conexion[] conexiones = new Conexion[POOL_SIZE];
	private static String connectionUrl = "jdbc:sqlserver://pollux.database.windows.net:1433;database=Interactive_Applications;user=TP_2018@pollux;password={yuM;3ZT3};encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";

	public static void init() throws Throwable {
		if (!INITIALIZED) {
			long tiempoNanoSegundos = getTime();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			showPerformance(tiempoNanoSegundos, ConexionPool.class + " init: pool de conexiones inicializado en");
			INITIALIZED = Boolean.TRUE;
		}
	}
	
	private static long getTime() {
		return System.nanoTime();
	}
	
	private static void showPerformance(long initialTime, String labelPrefix) {
		if (DEBUG) {
			long tiempoNanoSegundos = getTime();
			double tiempoTotalSegundos = (tiempoNanoSegundos - initialTime) / 1000000000.0;
			System.out.println("");
			System.out.println(labelPrefix.trim() + " " + tiempoTotalSegundos + " segundos");
			System.out.println("");
		}
	}

	public static synchronized Connection newConexion() throws SQLException {
		long tiempoInicialNanoSegundos = getTime();
		String labelPrefixNullConnection = ConexionPool.class + " newConexion: conexión NULL entregada en ";
		String labelPrefixConnection = ConexionPool.class + " newConexion: conexión entregada en ";
		
		if (ULTIMA_POSICION_LIBRE != (-1)) {
			boolean obtenida = false;
			Conexion conexion = conexiones[ULTIMA_POSICION_LIBRE];
			if (conexion == null) {
				conexion = new Conexion();
				conexiones[ULTIMA_POSICION_LIBRE] = conexion;
			}
			if (conexiones[ULTIMA_POSICION_LIBRE].getEstado() == false) {
				try {
					Connection connection = DriverManager.getConnection(connectionUrl);
					conexiones[ULTIMA_POSICION_LIBRE].setConexion(connection);
					conexiones[ULTIMA_POSICION_LIBRE].setEstado(true);
					conexiones[ULTIMA_POSICION_LIBRE].setPosicion(ULTIMA_POSICION_LIBRE);
					ULTIMA_POSICION_LIBRE++;
					if (ULTIMA_POSICION_LIBRE == POOL_SIZE) {
						ULTIMA_POSICION_LIBRE = (-1);
					}
					obtenida = true;
				} catch (SQLException e) {
					obtenida = false;
					e.printStackTrace();
				}
			}
			if (!obtenida) {
				if (RECURSIVIDAD) {
					showPerformance(tiempoInicialNanoSegundos, labelPrefixNullConnection);
					return null;
				}
				if (ULTIMA_POSICION_LIBRE != (-1)) {
					ULTIMA_POSICION_LIBRE = 0;
					boolean encontrada = false;
					for (int posicion = 0; ((!encontrada) && (posicion < POOL_SIZE)); posicion++) {
						if ((conexiones[posicion] == null) || ((conexiones[posicion] != null) && (conexiones[posicion].getEstado() == false))) {
							ULTIMA_POSICION_LIBRE = posicion;
							encontrada = true;
						}
					}
					RECURSIVIDAD = true;
					showPerformance(tiempoInicialNanoSegundos, labelPrefixConnection);
					return newConexion();
				}
			}
			RECURSIVIDAD = false;
			showPerformance(tiempoInicialNanoSegundos, labelPrefixConnection);
			return conexion.getConexion();
		}
		showPerformance(tiempoInicialNanoSegundos, labelPrefixNullConnection);
		return null;
	}

	public static synchronized void closeConexion(Connection conexion) {
		long tiempoInicialNanoSegundos = getTime();
		if (conexion != null) {
			boolean encontrado = false;
			for (int posicion = 0; ((!encontrado) && (posicion < POOL_SIZE)); posicion++) {
				if ((conexiones[posicion] != null) && (conexiones[posicion].getConexion() != null)
						&& (conexiones[posicion].getConexion().equals(conexion))) {
					try {
						conexiones[posicion].getConexion().close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					if (ULTIMA_POSICION_LIBRE == (-1)) {
						ULTIMA_POSICION_LIBRE = posicion;
					} else if (posicion < ULTIMA_POSICION_LIBRE) {
						ULTIMA_POSICION_LIBRE = posicion;
					}
					conexiones[posicion].setConexion(null);
					conexiones[posicion].setEstado(false);
					encontrado = true;
				}
			}
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		showPerformance(tiempoInicialNanoSegundos, ConexionPool.class + " closeConexion: conexión cerrada en");
	}
	
	public static synchronized void clean() {
		long tiempoInicialNanoSegundos = getTime();
		if ((conexiones != null) && (conexiones.length > 0)) {
			Conexion conexion = null;
			for (int posicion = 0; posicion < POOL_SIZE; posicion++) {
				conexion = conexiones[posicion];
				if (conexion != null) {
					try {
						conexion.getConexion().close();
					} catch (Throwable t) {
						t.printStackTrace();
					}
					if (ULTIMA_POSICION_LIBRE == (-1)) {
						ULTIMA_POSICION_LIBRE = posicion;
					} else if (posicion < ULTIMA_POSICION_LIBRE) {
						ULTIMA_POSICION_LIBRE = posicion;
					}
				}
				conexiones[posicion] = null;
			}
		}
		showPerformance(tiempoInicialNanoSegundos, ConexionPool.class + " clean: todas las conexiones cerradas en");
	}
}