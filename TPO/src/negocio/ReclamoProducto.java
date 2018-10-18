package negocio;

import java.sql.Date;

public class ReclamoProducto extends Reclamo {
	Producto prod;
	int cantidad;

	public ReclamoProducto(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}
	
	public void addProducto(Producto prod, int cantidad) {
		this.prod = prod;
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return this.prod;
	}
}
