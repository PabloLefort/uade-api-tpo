package excepciones;

public class ReclamoException extends Exception {
	private static final long serialVersionUID = -5681818342699360460L;
	
	public ReclamoException(String mensaje){
		super(mensaje);
	}
}