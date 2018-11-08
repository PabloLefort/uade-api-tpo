package negocio.strategy;

import negocio.Reclamo;

public class TratamientoDefault implements TratamientoStrategy {

	private Reclamo reclamo = null;

	public TratamientoDefault(Reclamo reclamo) {
		this.reclamo = reclamo;
	}

	@Override
	public void procesarReclamo() {
		System.out.println(reclamo.toString() + " " + this.getClass());
	}
}