package inventario;

public class TabelaHash {


    // Métricas
    private int operacoesInsercao = 0;
    private int operacoesBusca = 0;
    private int colisoes = 0;
    public enum MetodoHash {
        DIVISAO, MULTIPLICACAO
    }

    private Node[] tabela;
    private int tamanho;
    private int quantidade;
    private MetodoHash metodo;

    // Construtor parametrizável
    public TabelaHash(int tamanho, MetodoHash metodo) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
        this.quantidade = 0;
        this.metodo = metodo;
    }

    // Construtor padrão (usa DIVISAO)
    public TabelaHash(int tamanho) {
        this(tamanho, MetodoHash.DIVISAO);
    }

    // Hash por divisão
    private int hashDivisao(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = 31 * hash + chave.charAt(i);
        }
        return Math.abs(hash) % tamanho;
    }

    // Hash por multiplicação
    private int hashMultiplicacao(String chave) {
        int hash = 0;
        for (int i = 0; i < chave.length(); i++) {
            hash = 31 * hash + chave.charAt(i);
        }
        double A = 0.6180339887; // constante sugerida por Knuth
        double frac = (hash * A) % 1;
        return (int) Math.floor(Math.abs(frac) * tamanho);
    }

    private int hash(String chave) {
        switch (metodo) {
            case MULTIPLICACAO:
                return hashMultiplicacao(chave);
            case DIVISAO:
            default:
                return hashDivisao(chave);
        }
    }

    public void inserir(Item item) {
        int indice = hash(item.getNome());
        operacoesInsercao++;
        if (tabela[indice] != null) colisoes++;
        Node novo = new Node(item);
        novo.proximo = tabela[indice];
        tabela[indice] = novo;
        quantidade++;
    }

    public Item buscar(String nome) {
        int indice = hash(nome);
        operacoesBusca++;
        Node atual = tabela[indice];
        while (atual != null) {
            if (atual.item.getNome().equals(nome))
                return atual.item;
            atual = atual.proximo;
        }
        return null;
    }

    // Getters para métricas
    public int getOperacoesInsercao() { return operacoesInsercao; }
    public int getOperacoesBusca() { return operacoesBusca; }
    public int getColisoes() { return colisoes; }

    public int getQuantidade() {
        return quantidade;
    }
}
