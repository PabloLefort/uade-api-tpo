package negocio;

import java.sql.Date;

public class ReclamoFaltantes extends Reclamo {
	Factura factura;
	int cantidad;

	public ReclamoFaltantes(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}
	
	public void setFactura(Factura factura, int cantidad) {
		this.factura = factura;
		this.cantidad = cantidad;
	}

}
