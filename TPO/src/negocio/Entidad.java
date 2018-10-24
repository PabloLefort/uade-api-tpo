package negocio;

public class Entidad {
	protected int id;
	protected String descripcion;
	
	public int getId()
	{
		return this.id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public String getDescripcion()
	{
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
	}
}
