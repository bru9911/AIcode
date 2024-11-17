#Exemplo de código Java - Reflexão de um ponto em relação a um eixo:
#Este código vai simular a reflexão de um ponto 
(
𝑥
,
𝑦
)
(x,y) sobre o eixo 
𝑌
Y (espelho vertical). Em seguida, o programa imprimirá o ponto original e o ponto refletido.

import java.util.Scanner;

public class ReflexaoEspelho {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada dos dados do ponto
        System.out.print("Digite a coordenada x do ponto: ");
        double x = scanner.nextDouble();
        System.out.print("Digite a coordenada y do ponto: ");
        double y = scanner.nextDouble();

        // Reflexão do ponto sobre o eixo Y (espelho vertical)
