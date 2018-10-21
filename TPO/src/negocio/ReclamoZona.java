package negocio;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	private Zona zona;

	public ReclamoZona(Date fecha, int nroReclamo, String descripcion, Cliente cliente, Zona zona) {
		super(fecha, nroReclamo, descripcion, cliente);
		this.zona = zona;
	}
	
	public void setZona(Zona zona) {
		this.zona = zona;
	}

	public Zona getZona() {
		return this.zona;
	}

}
