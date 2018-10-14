package clases;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	String zona;

	public ReclamoZona(Date fecha, int nroReclamo, String descripcion, String estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}

}
