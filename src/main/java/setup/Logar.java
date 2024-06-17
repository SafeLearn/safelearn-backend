package setup;

import dao.LoginDao;

import java.util.Scanner;

public class Logar {
    private String nomeUsuario;
    private String senha;

    public Logar() {
        logar();
    }

    public void logar() {
        Scanner scanner = new Scanner(System.in);

        Boolean authenticated = false;

        do {
            System.out.println("Username: ");
            nomeUsuario = scanner.nextLine();

            System.out.println("Password: ");
            senha = scanner.nextLine();

            authenticated = new LoginDao().autenticarUsuario(nomeUsuario, senha);

            if (!authenticated) {
                System.out.println("Invalid credentials. Please try again.");
            }
        } while (!authenticated);

        System.out.println("Login successful!");
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }
}
