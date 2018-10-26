package negocio;

import java.sql.Date;

import services.TratamientoFactory;

public class ReclamoCantidades extends Reclamo {

	ItemReclamoCantidad item;

	public ReclamoCantidades(Date fecha, int nroReclamo, String descripcion, Cliente cliente,
			ItemReclamoCantidad item) {
		super(fecha, nroReclamo, descripcion, cliente);
		this.item = item;
		setTratamientoStrategy(TratamientoFactory.build(this));
	}

	public void addProducto(Producto prod, int cantidad) {
		this.item = new ItemReclamoCantidad(prod, cantidad);
	}

	public ItemReclamoCantidad getItemReclamoCantidad() {
		return this.item;
	}

	@Override
	public void procesar() {
		getTratamientoStrategy().procesarReclamo();
	}
}
