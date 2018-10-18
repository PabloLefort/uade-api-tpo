package negocio;

public class Rol {
	int cod;
	String descripcion;
	
	public Rol(int cod, String descripcion) {
		super();
		this.cod = cod;
		this.descripcion = descripcion;
	}

	public String getDescipcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
