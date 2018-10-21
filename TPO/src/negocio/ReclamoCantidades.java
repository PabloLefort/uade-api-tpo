package negocio;

import java.sql.Date;

public class ReclamoCantidades extends Reclamo {
	
	ItemReclamoCantidad item;	

	public ReclamoCantidades(Date fecha, int nroReclamo, String descripcion, Cliente cliente, ItemReclamoCantidad item) {
		super(fecha, nroReclamo, descripcion, cliente);
		this.item = item;
	}

	public void addProducto(Producto prod, int cantidad) {
		this.item = new ItemReclamoCantidad(prod, cantidad);
	}

	public ItemReclamoCantidad getItemReclamoCantidad() {
		return this.item;
	}	
}
