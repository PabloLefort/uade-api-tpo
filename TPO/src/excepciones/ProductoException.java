package excepciones;

public class ProductoException extends Exception {
	
	private static final long serialVersionUID = 1249236590156333203L;

	public ProductoException(String mensaje){
		super(mensaje);
	}
}