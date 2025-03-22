import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

// 📌 Produto Base
class Produto {
    private final int id;
    private final String nome;
    private final BigDecimal preco;

    public Produto(int id, String nome, BigDecimal preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s - R$ %.2f", id, nome, preco);
    }
}

// 📌 Classe de Carrinho de Compras
class Carrinho {
    private final Map<Produto, Integer> itens;

    public Carrinho() {
        this.itens = new HashMap<>();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        itens.put(produto, itens.getOrDefault(produto, 0) + quantidade);
    }

    public void removerProduto(Produto produto) {
        itens.remove(produto);
    }

    public BigDecimal calcularTotal() {
        return itens.entrySet().stream()
                .map(entry -> entry.getKey().getPreco().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void exibirCarrinho() {
        System.out.println("\n🛒 Seu Carrinho:");
        if (itens.isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }
        itens.forEach((produto, quantidade) -> 
            System.out.println(produto + " | Quantidade: " + quantidade)
        );
        System.out.println("Total: R$ " + calcularTotal());
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public void esvaziarCarrinho() {
        itens.clear();
    }
}

// 📌 Interface para estratégia de pagamento
interface MetodoPagamento {
    boolean processarPagamento(BigDecimal valor);
}

// 📌 Estratégia de pagamento por Cartão
class PagamentoCartao implements MetodoPagamento {
    @Override
    public boolean processarPagamento(BigDecimal valor) {
        System.out.println("💳 Processando pagamento via Cartão...");
        System.out.println("Pagamento de R$ " + valor + " aprovado!");
        return true;
    }
}

// 📌 Estratégia de pagamento via PIX
class PagamentoPIX implements MetodoPagamento {
    @Override
    public boolean processarPagamento(BigDecimal valor) {
        System.out.println("🔢 Gerando código PIX...");
        System.out.println("Pagamento de R$ " + valor + " realizado com sucesso!");
        return true;
    }
}

// 📌 Classe Loja Virtual
public class LojaVirtual {
    private static final List<Produto> CATALOGO = Arrays.asList(
            new Produto(1, "Notebook Gamer", BigDecimal.valueOf(5500.00)),
            new Produto(2, "Smartphone 5G", BigDecimal.valueOf(3500.00)),
            new Produto(3, "Fone Bluetooth", BigDecimal.valueOf(200.00)),
            new Produto(4, "Teclado Mecânico", BigDecimal.valueOf(350.00))
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carrinho carrinho = new Carrinho();

        while (true) {
            System.out.println("\n📌 MENU LOJA VIRTUAL:");
            System.out.println("1️⃣ Ver catálogo");
            System.out.println("2️⃣ Adicionar produto ao carrinho");
            System.out.println("3️⃣ Ver carrinho");
            System.out.println("4️⃣ Remover item do carrinho");
            System.out.println("5️⃣ Finalizar compra");
            System.out.println("6️⃣ Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1 -> exibirCatalogo();
                case 2 -> adicionarProdutoAoCarrinho(scanner, carrinho);
                case 3 -> carrinho.exibirCarrinho();
                case 4 -> removerProdutoDoCarrinho(scanner, carrinho);
                case 5 -> finalizarCompra(scanner, carrinho);
                case 6 -> {
                    System.out.println("👋 Obrigado por visitar nossa loja! Até logo.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("❌ Opção inválida, tente novamente.");
            }
        }
    }

    private static void exibirCatalogo() {
        System.out.println("\n📦 Catálogo de Produtos:");
        CATALOGO.forEach(System.out::println);
    }

    private static void adicionarProdutoAoCarrinho(Scanner scanner, Carrinho carrinho) {
        exibirCatalogo();
        System.out.print("Digite o ID do produto que deseja adicionar: ");
        int id = scanner.nextInt();
        System.out.print("Digite a quantidade: ");
        int quantidade = scanner.nextInt();

        Optional<Produto> produto = CATALOGO.stream().filter(p -> p.getId() == id).findFirst();
        if (produto.isPresent()) {
            carrinho.adicionarProduto(produto.get(), quantidade);
            System.out.println("✅ Produto adicionado ao carrinho!");
        } else {
            System.out.println("❌ Produto não encontrado.");
        }
    }

    private static void removerProdutoDoCarrinho(Scanner scanner, Carrinho carrinho) {
        carrinho.exibirCarrinho();
        if (carrinho.estaVazio()) return;

        System.out.print("Digite o ID do produto que deseja remover: ");
        int id = scanner.nextInt();

        Optional<Produto> produto = CATALOGO.stream().filter(p -> p.getId() == id).findFirst();
        if (produto.isPresent()) {
            carrinho.removerProduto(produto.get());
            System.out.println("🗑️ Produto removido do carrinho.");
        } else {
            System.out.println("❌ Produto não encontrado no carrinho.");
        }
    }

    private static void finalizarCompra(Scanner scanner, Carrinho carrinho) {
        if (carrinho.estaVazio()) {
            System.out.println("❌ O carrinho está vazio! Adicione produtos antes de finalizar.");
            return;
        }

        System.out.println("\n💰 Escolha o método de pagamento:");
        System.out.println("1️⃣ Cartão de Crédito/Débito");
        System.out.println("2️⃣ PIX");
        System.out.print("Opção: ");
        
        int opcaoPagamento = scanner.nextInt();
        MetodoPagamento metodoPagamento = switch (opcaoPagamento) {
            case 1 -> new PagamentoCartao();
            case 2 -> new PagamentoPIX();
            default -> {
                System.out.println("❌ Método inválido.");
                yield null;
            }
        };

        if (metodoPagamento != null && metodoPagamento.processarPagamento(carrinho.calcularTotal())) {
            System.out.println("🎉 Compra finalizada com sucesso!");
            carrinho.esvaziarCarrinho();
        } else {
            System.out.println("❌ Falha no pagamento.");
        }
    }
}
