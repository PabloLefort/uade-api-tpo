package negocio;

public class ItemFactura {
	private int cantidad;
	private int precio;
	
	public ItemFactura(int cantidad, int precio) {
		this.cantidad = cantidad;
		this.precio = precio;
	}
	
	public int calcularPrecio() {
		return precio * cantidad;
	}
}
