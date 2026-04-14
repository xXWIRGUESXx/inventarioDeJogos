import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        Personagem[] herois = new Personagem[5];
        herois[0] = new Personagem("Arthur", "Pistoleiro");
        herois[1] = new Personagem("Seraphina", "Cavaleira");
        herois[2] = new Personagem("Silas", "Fora da Lei");
        herois[3] = new Personagem("Elara", "Erudita");
        herois[4] = new Personagem("Thorne", "Mercenário");

        int escolha = -1;

        while (escolha != 0) {
            exibirMenu(herois);
            escolha = leitor.nextInt();

            switch (escolha) {
                case 1: case 2: case 3: case 4: case 5:
                    herois[escolha - 1].receberLoot(5);
                    break;

                case 6:
                    System.out.print("Ver inventário de qual herói (1-5)? ");
                    int h = leitor.nextInt();
                    herois[h-1].getInventario().exibirInventarioOrdenado();
                    break;

                case 0:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
        leitor.close();
    }

    private static void exibirMenu(Personagem[] herois) {
        System.out.println("\n--- SELEÇÃO DE PERSONAGEM ---");
        for (int i = 0; i < herois.length; i++) {
            System.out.println((i + 1) + ". " + herois[i].getNome() + " (" + herois[i].getClasse() + ")");
        }
        System.out.println("6. Exibir inventario ordenado");
        System.out.println("0. Encerrar Simulação");
        System.out.print("Escolha uma opção: ");
    }
}