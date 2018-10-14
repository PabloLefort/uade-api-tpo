package clases;

import java.sql.Date;

public class ReclamoFaltantes extends Reclamo {
	Factura factura;
	int cantidad;

	public ReclamoFaltantes(Date fecha, int nroReclamo, String descripcion, String estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}
	
	public void setFactura(Factura factura, int cantidad) {
		this.factura = factura;
		this.cantidad = cantidad;
	}

}
