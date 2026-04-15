
package inventario;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            System.err.println("Erro ao configurar UTF-8: " + e.getMessage());
        }
        // Parâmetros padrão
        long seed = System.currentTimeMillis();
        GeradorDeItens.MetodoRandom metodoRandom = GeradorDeItens.MetodoRandom.LCG;
        TabelaHash.MetodoHash metodoHash = TabelaHash.MetodoHash.DIVISAO;
        int tamanhoTabela = 101;

        // Leitura dos argumentos
        for (String arg : args) {
            if (arg.startsWith("seed=")) {
                seed = Long.parseLong(arg.substring(5));
            } else if (arg.equalsIgnoreCase("random=xorshift")) {
                metodoRandom = GeradorDeItens.MetodoRandom.XORSHIFT;
            } else if (arg.equalsIgnoreCase("random=lcg")) {
                metodoRandom = GeradorDeItens.MetodoRandom.LCG;
            } else if (arg.equalsIgnoreCase("hash=divisao")) {
                metodoHash = TabelaHash.MetodoHash.DIVISAO;
            } else if (arg.equalsIgnoreCase("hash=multiplicacao")) {
                metodoHash = TabelaHash.MetodoHash.MULTIPLICACAO;
            } else if (arg.startsWith("tamanho=")) {
                tamanhoTabela = Integer.parseInt(arg.substring(8));
            }
        }

        Scanner leitor = new Scanner(System.in);
        Personagem[] herois = new Personagem[5];
        for (int i = 0; i < herois.length; i++) {
            String[] nomes = {"Arthur", "Seraphina", "Silas", "Elara", "Thorne"};
            String[] classes = {"Pistoleiro", "Cavaleira", "Fora da Lei", "Erudita", "Mercenário"};
            herois[i] = new Personagem(nomes[i], classes[i], seed, metodoRandom, metodoHash, tamanhoTabela);
        }

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