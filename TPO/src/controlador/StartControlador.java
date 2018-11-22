package controlador;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import negocio.Cliente;
import negocio.ItemReclamoCantidad;
import negocio.Producto;
import negocio.ReclamoCantidades;
import negocio.ReclamoZona;
import negocio.Zona;
import pantallas.Home;
import services.ConexionPool;

public class StartControlador {

	public static void main(String[] args) {
		SistemaAdministracionReclamos controlador = new SistemaAdministracionReclamos();
		Home h = new Home();
		
		Cliente cliente_test = new Cliente("Pepe Pompin",
				"Avenida La Plata 945", "9999-9999", "test@gmail.com", 39000123); 
		Producto producto_test = new Producto(1, (float) 22.50, "producto 1",
				"test de alta de producto");
		Date now = new Date(7, 7, 1990);
		ItemReclamoCantidad item = new ItemReclamoCantidad(producto_test, 10);
		ReclamoCantidades reclamo = new ReclamoCantidades(now, 1, "asdasd", cliente_test, item);
		Zona zonaTest = new Zona(1, "Barracas");
		ReclamoZona recZona = new ReclamoZona(now, 2, "asdasd", cliente_test, zonaTest);
		List<ReclamoCantidades> reclamos = new ArrayList<ReclamoCantidades>();
		reclamos.add(reclamo);
		h.setReclamosCantidades(reclamos);
		List<ReclamoZona> reclamosZona = new ArrayList<ReclamoZona>();
		reclamosZona.add(recZona);
		h.setReclamosZona(reclamosZona);

		try {
			ConexionPool.init();
			controlador.Start();
			ConexionPool.clean();
		} catch (Throwable e) {
			System.out.println(e.getMessage());
		}
	}

}
