package negocio.strategy;

import negocio.Reclamo;
import negocio.ReclamoCantidades;
import negocio.ReclamoFacturacion;
import negocio.ReclamoFaltantes;
import negocio.ReclamoProducto;
import negocio.ReclamoZona;

public class TratamientoFactory {

	public static TratamientoStrategy build(Reclamo reclamo) {
		if (reclamo instanceof ReclamoFacturacion) {
			return new TratamientoDefault(reclamo);
		} else if (reclamo instanceof ReclamoFaltantes) {
			return new TratamientoDefault(reclamo);
		} else if (reclamo instanceof ReclamoProducto) {
			return new TratamientoProducto(((ReclamoProducto) reclamo));
		}else if (reclamo instanceof ReclamoZona) {
			return new TratamientoProducto(((ReclamoZona) reclamo));
		}else if (reclamo instanceof ReclamoCantidades) {
			return new TratamientoProducto(((ReclamoCantidades) reclamo));
		}
		return null;
	}
}
