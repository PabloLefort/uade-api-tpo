package clases;

import java.sql.Date;
import java.util.Collection;

public class ReclamoFacturacion extends Reclamo {
	Collection<Factura> facturas;

	public ReclamoFacturacion(Date fecha, int nroReclamo, String descripcion, String estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}

	public void addFactura(Factura factura) {
		this.facturas.add(factura);
	}
}
