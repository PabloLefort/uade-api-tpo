package excepciones;

public class ClienteException extends Exception {
	
	private static final long serialVersionUID = 1249236590156333203L;

	public ClienteException(String mensaje){
		super(mensaje);
	}
}