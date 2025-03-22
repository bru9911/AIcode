import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;

// 📌 Classe Usuario (modelo de usuário)
class Usuario {
    private final String username;
    private final String senhaHash; // Senha criptografada

    public Usuario(String username, String senha) {
        this.username = username;
        this.senhaHash = criptografarSenha(senha);
    }

    private String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(12)); // Criptografando a senha com salt
    }

    public String getUsername() {
        return username;
    }

    public boolean validarSenha(String senhaDigitada) {
        return BCrypt.checkpw(senhaDigitada, senhaHash);
    }
}

// 📌 Classe GerenciadorUsuarios (simulando um banco de dados)
class GerenciadorUsuarios {
    private final Map<String, Usuario> usuarios;

    public GerenciadorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public boolean cadastrarUsuario(String username, String senha) {
        if (usuarios.containsKey(username)) {
            return false; // Usuário já existe
        }
        usuarios.put(username, new Usuario(username, senha));
        return true;
    }

    public Usuario autenticarUsuario(String username, String senha) {
        Usuario usuario = usuarios.get(username);
        if (usuario != null && usuario.validarSenha(senha)) {
            return usuario;
        }
        return null;
    }
}

// 📌 Classe principal com menu interativo
public class SistemaLogin {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorUsuarios gerenciadorUsuarios = new GerenciadorUsuarios();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🔑 SISTEMA DE LOGIN");
            System.out.println("1️⃣ Cadastrar usuário");
            System.out.println("2️⃣ Fazer login");
            System.out.println("3️⃣ Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> cadastrarUsuario();
                case 2 -> fazerLogin();
                case 3 -> {
                    System.out.println("👋 Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private static void cadastrarUsuario() {
        System.out.print("\n👤 Escolha um nome de usuário: ");
        String username = scanner.nextLine();

        System.out.print("🔒 Escolha uma senha: ");
        String senha = scanner.nextLine();

        if (gerenciadorUsuarios.cadastrarUsuario(username, senha)) {
            System.out.println("✅ Usuário cadastrado com sucesso!");
        } else {
            System.out.println("❌ Nome de usuário já existe. Tente outro.");
        }
    }

    private static void fazerLogin() {
        System.out.print("\n👤 Usuário: ");
        String username = scanner.nextLine();

        System.out.print("🔑 Senha: ");
        String senha = scanner.nextLine();

        Usuario usuario = gerenciadorUsuarios.autenticarUsuario(username, senha);
        if (usuario != null) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo, " + usuario.getUsername() + "!");
        } else {
            System.out.println("❌ Usuário ou senha incorretos.");
        }
    }
}
