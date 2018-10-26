package services;

import java.util.Scanner;

import dao.LoginDAO;
import negocio.Usuario;

public class Login {

	private static Boolean INITIALIZED = Boolean.FALSE;
	private static LoginDAO login = null;
	private static Scanner scanner = new Scanner(System.in);

	public static void init() throws Throwable {
		if (!INITIALIZED) {
			INITIALIZED = Boolean.TRUE;
		}
	}

	public static Usuario login(String email, String password) {

		// si el formulario falla
		while (email.isEmpty() || password.isEmpty()) {
			System.out.println("Error: Complete los datos solicitados");
			System.out.println("Ingrese usuario: ");
			email = scanner.nextLine();
			System.out.println("Ingrese password: ");
			password = scanner.nextLine();
			scanner.close();
		}
		// si el formulario pasa la validacion
		try {

			return login.autenticarUsuario(email, password);

		} catch (Exception e) {

			System.out.println(e.getMessage());
		}

		return null;
	}
}
