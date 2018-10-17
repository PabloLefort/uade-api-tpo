package negocio;

public class Usuario {
	private String nombre;
	private String email;
	private String password;
	private Rol rol;
	
	public Usuario(String nombre, String email, String password, Rol rol) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.rol = rol;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setRol(Rol rol) {
		this.rol = rol;
	}
	
	public Rol getRol() {
		return this.rol;
	}
	
}
