package test;

import controlador.SistemaAdministracionReclamos;
import excepciones.AccesoException;
import excepciones.ClienteException;
import excepciones.ConexionException;
import view.ClienteView;

public class Test {

	public static void main(String[] args) throws ConexionException, AccesoException, ClienteException {
		ClienteView cli = null;
		
		try {
			cli = new SistemaAdministracionReclamos().buscarCliente(32444444);

			System.out.println(cli.getNombre());
		}
		catch (Exception e) {
			//mensaje de error
		}
		
	}

}
