package negocio.strategy;

import negocio.Reclamo;
import negocio.ReclamoProducto;

public class TratamientoProducto implements TratamientoStrategy {

	private Reclamo reclamo = null;

	public TratamientoProducto(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	@Override
	public void procesarReclamo() {
		System.out.println(((ReclamoProducto) reclamo).toString() + " " + this.getClass());
	}
}
