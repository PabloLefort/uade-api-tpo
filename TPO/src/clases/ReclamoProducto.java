package clases;

import java.sql.Date;

public class ReclamoProducto extends Reclamo {
	Producto prod;
	int cantidad;

	public ReclamoProducto(Date fecha, int nroReclamo, String descripcion, String estado) {
		super(fecha, nroReclamo, descripcion, estado);
		// TODO Auto-generated constructor stub
	}
	
	public void addProducto(Producto prod, int cantidad) {
		this.prod = prod;
		this.cantidad = cantidad;
	}
}
