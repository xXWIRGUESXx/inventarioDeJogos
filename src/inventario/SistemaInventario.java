package inventario;

public class SistemaInventario {
    private TabelaHash buscaRapida;
    private MaxHeap rankingRaridade;
    private GeradorDeItens gerador;

    // Novo construtor parametrizável
    public SistemaInventario(long seed, GeradorDeItens.MetodoRandom metodoRandom, TabelaHash.MetodoHash metodoHash, int tamanhoTabela) {
        this(seed, metodoRandom, metodoHash, tamanhoTabela, tamanhoTabela * 2);
    }

    public SistemaInventario(long seed, GeradorDeItens.MetodoRandom metodoRandom, TabelaHash.MetodoHash metodoHash, int tamanhoTabela, int capacidadeHeap) {
        this.buscaRapida = new TabelaHash(tamanhoTabela, metodoHash);
        this.rankingRaridade = new MaxHeap(capacidadeHeap);
        this.gerador = new GeradorDeItens(seed, metodoRandom);
    }

    // Construtor padrão
    public SistemaInventario() {
        this(System.currentTimeMillis(), GeradorDeItens.MetodoRandom.LCG, TabelaHash.MetodoHash.DIVISAO, 101);
    }

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
    // Getters para métricas da tabela hash
    public int getOperacoesInsercao() { return buscaRapida.getOperacoesInsercao(); }
    public int getOperacoesBusca() { return buscaRapida.getOperacoesBusca(); }
    public int getColisoes() { return buscaRapida.getColisoes(); }
}
