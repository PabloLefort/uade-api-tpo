package controlador;

import services.ConexionPool;
import services.Login;

public class StartControlador {

	public static void main(String[] args) {
		SistemaAdministracionReclamos controlador = new SistemaAdministracionReclamos();
		try {
			ConexionPool.init();
			Login.init();
			controlador.Start();
			ConexionPool.clean();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}
