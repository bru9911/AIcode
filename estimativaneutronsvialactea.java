public class NucleonsNaViaLactea {

    public static void main(String[] args) {
        // Constantes
        double massaEstrela = 1.99e30; // Massa de uma estrela em kg (aproximadamente 1 massa solar)
        double massaAtomicaHidrogenio = 1.67e-27; // Massa de um átomo de hidrogênio em kg
        double nEstrelas = 2e11; // Número estimado de estrelas na Via Láctea (200 bilhões)

        // Estimativa de número de nêutrons por estrela
        // Considerando que a maior parte da massa de uma estrela é hidrogênio
        double nucleonsPorEstrela = massaEstrela / massaAtomicaHidrogenio;  // número de átomos por estrela
        double neutronsPorEstrela = nucleonsPorEstrela / 2;  // estimativa de nêutrons (aproximadamente 50%)

        // Total de nêutrons na Via Láctea
        double totalNeutrons = neutronsPorEstrela * nEstrelas;

        // Exibindo o resultado
        System.out.printf("Estimativa do número de nêutrons na Via Láctea: %.3e%n", totalNeutrons);
    }
}
