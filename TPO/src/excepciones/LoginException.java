package excepciones;

public class LoginException extends Exception {

    private static final long serialVersionUID = 5190329702485382960L;

    public LoginException(String mensaje){
        super(mensaje);
    }
}
