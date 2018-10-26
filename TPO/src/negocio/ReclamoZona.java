package negocio;

import java.sql.Date;

import services.TratamientoFactory;

public class ReclamoZona extends Reclamo {
	private Zona zona;

	public ReclamoZona(Date fecha, int nroReclamo, String descripcion, Cliente cliente, Zona zona) {
		super(fecha, nroReclamo, descripcion, cliente);
		setTratamientoStrategy(TratamientoFactory.build(this));
		this.zona = zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Zona getZona() {
		return this.zona;
	}

	@Override
	public void procesar() {
		getTratamientoStrategy().procesarReclamo();
	}
}
