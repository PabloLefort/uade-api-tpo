package testPool.main;

import java.sql.Connection;

import dao.ConexionPool;

public class TestPool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			System.out.println("HOLA !");
			ConexionPool.init();
			Connection connection1 = ConexionPool.newConexion();
			Connection connection2 = ConexionPool.newConexion();
			Connection connection3 = ConexionPool.newConexion();
			Connection connection4 = ConexionPool.newConexion();
			Connection connection5 = ConexionPool.newConexion();
			Connection connection6 = ConexionPool.newConexion();
			ConexionPool.closeConexion(connection1);
			ConexionPool.closeConexion(connection2);
			ConexionPool.closeConexion(connection3);
			ConexionPool.closeConexion(connection4);
			ConexionPool.closeConexion(connection5);
			ConexionPool.closeConexion(connection6);
			connection1 = ConexionPool.newConexion();
			connection2 = ConexionPool.newConexion();
			connection3 = ConexionPool.newConexion();
			connection4 = ConexionPool.newConexion();
			connection5 = ConexionPool.newConexion();
			connection6 = ConexionPool.newConexion();
			ConexionPool.closeConexion(connection1);
			ConexionPool.closeConexion(connection2);
			ConexionPool.closeConexion(connection3);
			ConexionPool.closeConexion(connection4);
			ConexionPool.closeConexion(connection5);
			ConexionPool.closeConexion(connection6);
			connection1 = ConexionPool.newConexion();
			connection2 = ConexionPool.newConexion();
			connection3 = ConexionPool.newConexion();
			connection4 = ConexionPool.newConexion();
			connection5 = ConexionPool.newConexion();
			connection6 = ConexionPool.newConexion();
			ConexionPool.closeConexion(connection5);
			connection5 = ConexionPool.newConexion();
			ConexionPool.clean();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
