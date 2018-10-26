package negocio;

import java.sql.Date;
import java.util.Collection;

import negocio.strategy.TratamientoFactory;

public class ReclamoFacturacion extends Reclamo {
	Collection<Factura> facturas;

	public ReclamoFacturacion(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
		setTratamientoStrategy(TratamientoFactory.build(this));
	}

	public void addFactura(Factura factura) {
		this.facturas.add(factura);
	}

	public Collection<Factura> getFacturas() {
		return this.facturas;
	}

	@Override
	public void procesar() {
		getTratamientoStrategy().procesarReclamo();
	}
}
