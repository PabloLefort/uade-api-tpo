package negocio;

import java.sql.Date;

public class ReclamoProducto extends Reclamo {
	ItemReclamoProducto item;	

	public ReclamoProducto(Date fecha, int nroReclamo, String descripcion, Cliente cliente, ItemReclamoProducto item) {
		super(fecha, nroReclamo, descripcion, cliente);
		this.item = item;
	}
	
	public void addItemReclamoProducto(Producto prod, int cantidad) {
		item.setProducto(prod);
		item.setCantidad(cantidad);
	}

	public ItemReclamoProducto getItemReclamoProducto() {
		return this.item;
	}
}
