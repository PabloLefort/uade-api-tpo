package negocio;

import java.sql.Date;

public class ReclamoZona extends Reclamo {
	String zona;

	public ReclamoZona(Date fecha, int nroReclamo, String descripcion, EstadosReclamo estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}
	
	public void setZona(String zona) {
		this.zona = zona;
	}

}
