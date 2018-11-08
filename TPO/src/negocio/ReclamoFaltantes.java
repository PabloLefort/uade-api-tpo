package negocio;

import java.sql.Date;

import negocio.strategy.TratamientoFactory;

public class ReclamoFaltantes extends Reclamo {
	Factura factura;
	int cantidad;

	public ReclamoFaltantes(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
		setTratamientoStrategy(TratamientoFactory.build(this));
	}

	public void setFactura(Factura factura, int cantidad) {
		this.factura = factura;
		this.cantidad = cantidad;
	}

	public Factura getFactura() {
		return this.factura;
	}

	public int getCantidad() {
		return this.cantidad;
	}

	@Override
	public void procesar() {
		getTratamientoStrategy().procesarReclamo();
	}
}
