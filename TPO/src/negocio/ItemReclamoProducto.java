package negocio;

public class ItemReclamoProducto {
	private Producto prod;
	private int cantidad;
	
	public ItemReclamoProducto(Producto prod, int cantidad) {
		this.prod = prod;
		this.cantidad = cantidad;
	}
	
	public int getCantidad() {
		return this.cantidad;
	}

	public Producto getProducto() {
		return this.prod;
	}
	
	public void setProducto(Producto prod) {
		this.prod = prod;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
