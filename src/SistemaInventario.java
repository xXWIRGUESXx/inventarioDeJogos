public class SistemaInventario {
    private TabelaHash buscaRapida = new TabelaHash(101);
    private MaxHeap rankingRaridade = new MaxHeap(100);
    private GeradorDeItens gerador = new GeradorDeItens();

    public void executarDrop() {
        Item novo = gerador.gerarDrop();
        buscaRapida.inserir(novo);
        rankingRaridade.inserir(novo);
        System.out.println("Dropado: " + novo.getNome());
    }

    public void executarMultiploDrop(int quantidade) {
        for (int i = 0; i < quantidade; i++) {
            executarDrop();
        }
    }
    public void exibirInventarioOrdenado() {
        System.out.println("\n--- Inventário por Raridade (Sorted) ---");

        int totalNoMomento = rankingRaridade.getQuantidade();

        for (int i = 0; i < totalNoMomento; i++) {
            Item it = rankingRaridade.extrairMax();
            System.out.println("[" + it.getRaridade() + "] " + it.getNome());
        }
    }

    public Item verMaisRaro() {
        return rankingRaridade.maisRaro();
    }
    public Item buscarNaHash(String nome) {
        return buscaRapida.buscar(nome);
    }
}
