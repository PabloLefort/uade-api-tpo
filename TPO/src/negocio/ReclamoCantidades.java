package negocio;

import java.sql.Date;

public class ReclamoCantidades extends Reclamo {
	Producto prod;
	ItemReclamoCantidad item;
	
	public ReclamoCantidades(Date fecha, int nroReclamo, String descripcion, EstadosReclamo estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}

	public void addProducto(Producto prod, int cantidad) {
		this.prod = prod;
		this.item = new ItemReclamoCantidad(cantidad);
	}	
}
