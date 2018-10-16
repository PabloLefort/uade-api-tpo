package negocio;

import java.sql.Date;
import java.util.Collection;

public class ReclamoFacturacion extends Reclamo {
	Collection<Factura> facturas;

	public ReclamoFacturacion(Date fecha, int nroReclamo, String descripcion, EstadosReclamo estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}

	public void addFactura(Factura factura) {
		this.facturas.add(factura);
	}
}
