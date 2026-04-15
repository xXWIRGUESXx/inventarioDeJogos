

package inventario;

import inventario.GeradorDeItens;
import inventario.TabelaHash;
import inventario.SistemaInventario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;

public class TesteDesempenho {
    public static void main(String[] args) {
        // Parâmetros padrão
        long[] seeds = {42};
        GeradorDeItens.MetodoRandom[] randoms = {GeradorDeItens.MetodoRandom.LCG};
        TabelaHash.MetodoHash[] hashes = {TabelaHash.MetodoHash.DIVISAO};
        int[] tamanhos = {10};
        int drops = 20;

        // Se argumentos forem fornecidos, usar para sobrescrever os parâmetros
        if (args.length >= 5) {
            try {
                drops = Integer.parseInt(args[0]);
                tamanhos = new int[]{Integer.parseInt(args[1])};
                randoms = new GeradorDeItens.MetodoRandom[]{GeradorDeItens.MetodoRandom.valueOf(args[2].toUpperCase())};
                hashes = new TabelaHash.MetodoHash[]{TabelaHash.MetodoHash.valueOf(args[3].toUpperCase())};
                seeds = new long[]{Long.parseLong(args[4])};
            } catch (Exception e) {
                System.err.println("Argumentos inválidos. Uso: <drops> <tamanho> <random> <hash> <seed>");
                return;
            }
        }

        try (FileWriter fw = new FileWriter("resultados_teste.csv")) {
            fw.write("Seed,Random,Hash,Tabela,Drops,Tempo_ms,Mem_KB,Insercoes,Buscas,Colisoes\n");
            for (long seed : seeds) {
                for (GeradorDeItens.MetodoRandom random : randoms) {
                    for (TabelaHash.MetodoHash hash : hashes) {
                        for (int tamanho : tamanhos) {
                            System.gc();
                            long memAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            long ini = System.nanoTime();
                            SistemaInventario inv = new SistemaInventario(seed, random, hash, tamanho, drops);
                            for (int i = 0; i < drops; i++) {
                                inv.executarDrop();
                            }
                            long fim = System.nanoTime();
                            long memDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
                            // Coletar métricas
                            int insercoes = inv.getOperacoesInsercao();
                            int buscas = inv.getOperacoesBusca();
                            int colisoes = inv.getColisoes();
                            String linha = String.format(Locale.US, "%d,%s,%s,%d,%d,%.2f,%.2f,%d,%d,%d\n",
                                    seed, random, hash, tamanho, drops, (fim-ini)/1e6, (memDepois-memAntes)/1024.0, insercoes, buscas, colisoes);
                            fw.write(linha);
                            System.out.print(linha);
                        }
                    }
                }
            }
            System.out.println("\nResultados exportados para resultados_teste.csv");
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo de resultados: " + e.getMessage());
        }
    }
}
