package negocio;

import java.sql.Date;
import java.util.Collection;

public class ReclamoFacturacion extends Reclamo {
	Collection<Factura> facturas;

	public ReclamoFacturacion(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}

	public void addFactura(Factura factura) {
		this.facturas.add(factura);
	}
}
