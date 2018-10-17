package negocio;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	String zona;

	public ReclamoZona(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}

}
