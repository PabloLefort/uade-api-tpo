package negocio;

import java.sql.Date;
import java.util.Collection;

public class ReclamoCompuesto extends Reclamo {
	Collection<Reclamo> reclamos;

	public ReclamoCompuesto(Date fecha, int nroReclamo, String descripcion, EstadosReclamo estado) {
		super(fecha, nroReclamo, descripcion, estado);
	}

	public void add(Reclamo reclamo) {
		this.reclamos.add(reclamo);
	}
	
	public void delete(Reclamo reclamo) {
		this.reclamos.remove(reclamo);
	}
	
	public void cerrarReclamo() {
		this.reclamos.forEach(Reclamo::cerrarReclamo);
	}
}