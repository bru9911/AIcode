public class PesoDaLua {

    public static void main(String[] args) {
        // Constantes
        double G = 6.67430e-11; // Constante gravitacional em N(m^2)/(kg^2)
        double massaTerra = 5.97e24; // Massa da Terra em kg
        double massaLua = 7.35e22; // Massa da Lua em kg
        double distanciaTerraLua = 3.84e8; // Distância média entre a Terra e a Lua em metros

        // Calculando a força gravitacional entre a Terra e a Lua (peso da Lua)
        double pesoLua = G * (massaTerra * massaLua) / (distanciaTerraLua * distanciaTerraLua);

        // Exibindo o resultado
        System.out.printf("O peso da Lua em relação à Terra é: %.3e N%n", pesoLua);
    }
}
