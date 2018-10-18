package dao;

import java.sql.Connection;

import dao.ConexionPool;

public class TestPool {

	public static void main(String[] args) {
		try {
			System.out.println("HOLA !");
			ConexionPool conexionPool = ConexionPool.build();
			Connection connection1 = conexionPool.newConexion();
			Connection connection2 = conexionPool.newConexion();
			Connection connection3 = conexionPool.newConexion();
			Connection connection4 = conexionPool.newConexion();
			Connection connection5 = conexionPool.newConexion();
			conexionPool.closeConexion(connection1);
			conexionPool.closeConexion(connection2);
			conexionPool.closeConexion(connection3);
			conexionPool.closeConexion(connection4);
			conexionPool.closeConexion(connection5);
			connection1 = conexionPool.newConexion();
			connection2 = conexionPool.newConexion();
			connection3 = conexionPool.newConexion();
			connection4 = conexionPool.newConexion();
			connection5 = conexionPool.newConexion();
			conexionPool.closeConexion(connection1);
			conexionPool.closeConexion(connection2);
			conexionPool.closeConexion(connection3);
			conexionPool.closeConexion(connection4);
			conexionPool.closeConexion(connection5);
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
