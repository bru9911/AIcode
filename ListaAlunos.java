import java.util.*;
import org.mindrot.jbcrypt.BCrypt;

// 📌 Classe Aluno (modelo)
class Aluno {
    private final String nome;
    private final String ra; // RA (Registro Acadêmico) deve ser único
    private final String senhaHash; // Senha criptografada

    public Aluno(String nome, String ra, String senha) {
        this.nome = nome;
        this.ra = ra;
        this.senhaHash = criptografarSenha(senha);
    }

    private String criptografarSenha(String senha) {
        return BCrypt.hashpw(senha, BCrypt.gensalt(12)); // Criptografia segura com salt
    }

    public String getNome() {
        return nome;
    }

    public String getRa() {
        return ra;
    }

    public boolean validarSenha(String senhaDigitada) {
        return BCrypt.checkpw(senhaDigitada, senhaHash);
    }

    @Override
    public String toString() {
        return String.format("RA: %s | Nome: %s", ra, nome);
    }
}

// 📌 Classe GerenciadorAlunos (simulando um banco de dados)
class GerenciadorAlunos {
    private final Map<String, Aluno> alunos; // Usa um Map para garantir RAs únicos

    public GerenciadorAlunos() {
        this.alunos = new HashMap<>();
    }

    public boolean cadastrarAluno(String nome, String ra, String senha) {
        if (alunos.containsKey(ra)) {
            return false; // RA já cadastrado
        }
        alunos.put(ra, new Aluno(nome, ra, senha));
        return true;
    }

    public Aluno autenticarAluno(String ra, String senha) {
        Aluno aluno = alunos.get(ra);
        if (aluno != null && aluno.validarSenha(senha)) {
            return aluno;
        }
        return null;
    }

    public Optional<Aluno> buscarAluno(String ra) {
        return Optional.ofNullable(alunos.get(ra));
    }

    public void listarAlunos() {
        if (alunos.isEmpty()) {
            System.out.println("📭 Nenhum aluno cadastrado.");
        } else {
            System.out.println("\n📜 Lista de Alunos:");
            alunos.values().forEach(System.out::println);
        }
    }
}

// 📌 Classe principal com menu interativo
public class SistemaAlunos {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GerenciadorAlunos gerenciador = new GerenciadorAlunos();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🎓 SISTEMA DE GERENCIAMENTO DE ALUNOS");
            System.out.println("1️⃣ Cadastrar aluno");
            System.out.println("2️⃣ Fazer login");
            System.out.println("3️⃣ Buscar aluno por RA");
            System.out.println("4️⃣ Listar todos os alunos");
            System.out.println("5️⃣ Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> cadastrarAluno();
                case 2 -> fazerLogin();
                case 3 -> buscarAluno();
                case 4 -> gerenciador.listarAlunos();
                case 5 -> {
                    System.out.println("👋 Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private static void cadastrarAluno() {
        System.out.print("\n👤 Nome do aluno: ");
        String nome = scanner.nextLine();

        System.out.print("📚 RA (Registro Acadêmico): ");
        String ra = scanner.nextLine();

        System.out.print("🔒 Crie uma senha: ");
        String senha = scanner.nextLine();

        if (gerenciador.cadastrarAluno(nome, ra, senha)) {
            System.out.println("✅ Aluno cadastrado com sucesso!");
        } else {
            System.out.println("❌ RA já cadastrado. Escolha outro.");
        }
    }

    private static void fazerLogin() {
        System.out.print("\n🎓 Digite seu RA: ");
        String ra = scanner.nextLine();

        System.out.print("🔑 Digite sua senha: ");
        String senha = scanner.nextLine();

        Aluno aluno = gerenciador.autenticarAluno(ra, senha);
        if (aluno != null) {
            System.out.println("✅ Login bem-sucedido! Bem-vindo, " + aluno.getNome() + "!");
        } else {
            System.out.println("❌ RA ou senha incorretos.");
        }
    }

    private static void buscarAluno() {
        System.out.print("\n🔍 Digite o RA do aluno: ");
        String ra = scanner.nextLine();

        Optional<Aluno> aluno = gerenciador.buscarAluno(ra);
        aluno.ifPresentOrElse(
                a -> System.out.println("✅ Aluno encontrado: " + a),
                () -> System.out.println("❌ Aluno não encontrado.")
        );
    }
}
