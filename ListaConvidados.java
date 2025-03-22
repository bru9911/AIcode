import java.util.*;

class Convidado implements Comparable<Convidado> {
    private final String nome;
    private final int idade;

    public Convidado(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public int compareTo(Convidado outro) {
        return this.nome.compareToIgnoreCase(outro.nome); // Ordenação alfabética
    }

    @Override
    public String toString() {
        return String.format("%s (Idade: %d)", nome, idade);
    }
}

class ListaConvidados {
    private final Set<Convidado> convidados; // Usa Set para evitar duplicados

    public ListaConvidados() {
        this.convidados = new TreeSet<>(); // TreeSet mantém a ordem alfabética
    }

    public boolean adicionarConvidado(String nome, int idade) {
        return convidados.add(new Convidado(nome, idade));
    }

    public boolean removerConvidado(String nome) {
        return convidados.removeIf(convidado -> convidado.getNome().equalsIgnoreCase(nome));
    }

    public Optional<Convidado> buscarConvidado(String nome) {
        return convidados.stream()
                .filter(convidado -> convidado.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public void exibirLista() {
        if (convidados.isEmpty()) {
            System.out.println("📭 A lista de convidados está vazia.");
        } else {
            System.out.println("\n📜 Lista de Convidados:");
            convidados.forEach(System.out::println);
        }
    }
}

public class GerenciadorConvidados {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ListaConvidados lista = new ListaConvidados();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n🎉 GERENCIADOR DE CONVIDADOS 🎉");
            System.out.println("1️⃣ Adicionar convidado");
            System.out.println("2️⃣ Remover convidado");
            System.out.println("3️⃣ Buscar convidado");
            System.out.println("4️⃣ Exibir lista");
            System.out.println("5️⃣ Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1 -> adicionarConvidado();
                case 2 -> removerConvidado();
                case 3 -> buscarConvidado();
                case 4 -> lista.exibirLista();
                case 5 -> {
                    System.out.println("👋 Saindo do sistema...");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private static void adicionarConvidado() {
        System.out.print("\n👤 Nome do convidado: ");
        String nome = scanner.nextLine();

        System.out.print("🎂 Idade: ");
        int idade = scanner.nextInt();

        if (lista.adicionarConvidado(nome, idade)) {
            System.out.println("✅ Convidado adicionado!");
        } else {
            System.out.println("⚠️ Convidado já está na lista.");
        }
    }

    private static void removerConvidado() {
        System.out.print("\n🗑️ Nome do convidado a remover: ");
        String nome = scanner.nextLine();

        if (lista.removerConvidado(nome)) {
            System.out.println("✅ Convidado removido.");
        } else {
            System.out.println("❌ Convidado não encontrado.");
        }
    }

    private static void buscarConvidado() {
        System.out.print("\n🔍 Nome do convidado: ");
        String nome = scanner.nextLine();

        Optional<Convidado> convidado = lista.buscarConvidado(nome);
        convidado.ifPresentOrElse(
                c -> System.out.println("✅ Convidado encontrado: " + c),
                () -> System.out.println("❌ Convidado não encontrado.")
        );
    }
}
