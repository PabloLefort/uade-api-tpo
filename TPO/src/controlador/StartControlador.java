package controlador;

public class StartControladdor {

	public static void main(String[] args) {
		private SistemaAdministracionReclamos controlador = new SistemaAdministracionReclamos();
		ConexionPool.init();
		controlador.Start();
		ConexionPool.clean();
	}

}
