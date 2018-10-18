package negocio;

import java.sql.Date;

public class ReclamoCantidades extends Reclamo {
	
	Producto prod;
	ItemReclamoCantidad item;

	public ReclamoCantidades(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}

	public void addProducto(Producto prod, int cantidad) {
		this.prod = prod;
		this.item = new ItemReclamoCantidad(cantidad);
	}

	public ItemReclamoCantidad getItemReclamo() {
		return this.item;
	}	
}
