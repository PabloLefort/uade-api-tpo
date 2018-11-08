package negocio;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

public class ReclamoFacturacion extends Reclamo {
	Collection<ItemFactura> itemfacturas;

	public ReclamoFacturacion(Date fecha, int nroReclamo, String descripcion, Cliente cliente) {
		super(fecha, nroReclamo, descripcion, cliente);
	}

	public void addItemFactura(ItemFactura itemFactura) {
		this.itemfacturas.add(itemFactura);
	}

	public Collection<ItemFactura> getItemsFacturas() {
		return this.itemfacturas;
	}
	
	public Collection<Factura> getFacturas(){
		Collection<Factura> facturas = null;
		for (Iterator iterator = this.itemfacturas.iterator(); iterator.hasNext();) {
			Factura factura = (Factura) iterator.next();
			facturas.add(factura);
		}
		return facturas;
	}

	@Override
	public String getTipoReclamo() {
		return TiposReclamo.FACTURACION.toString();
	}
}
