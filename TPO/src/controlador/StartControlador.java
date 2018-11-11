package controlador;


import services.ConexionPool;

public class StartControlador {

	public static void main(String[] args) {
		SistemaAdministracionReclamos controlador = new SistemaAdministracionReclamos();
		try {
			ConexionPool.init();
			controlador.Start();
			ConexionPool.clean();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}
