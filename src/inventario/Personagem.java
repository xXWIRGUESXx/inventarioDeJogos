package inventario;

public class Personagem {
    private String nome;
    private String classe;
    private SistemaInventario inventario;

    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.inventario = new SistemaInventario();
    }

    // construtor parametrizável
    public Personagem(String nome, String classe, long seed, GeradorDeItens.MetodoRandom metodoRandom, TabelaHash.MetodoHash metodoHash, int tamanhoTabela) {
        this(nome, classe, seed, metodoRandom, metodoHash, tamanhoTabela, tamanhoTabela * 2);
    }

    // construtor parametrizável com capacidade do heap
    public Personagem(String nome, String classe, long seed, GeradorDeItens.MetodoRandom metodoRandom, TabelaHash.MetodoHash metodoHash, int tamanhoTabela, int capacidadeHeap) {
        this.nome = nome;
        this.classe = classe;
        this.inventario = new SistemaInventario(seed, metodoRandom, metodoHash, tamanhoTabela, capacidadeHeap);
    }

    public String getNome() {
        return nome;
    }
    public String getClasse() {
        return classe;
    }
    public SistemaInventario getInventario() {
        return inventario;
    }

    public void receberLoot(int quantidade) {
        System.out.println("\n--- " + nome + " (" + classe + ") abre um baú! ---");
        inventario.executarMultiploDrop(quantidade);

        Item melhor = inventario.verMaisRaro();
        System.out.println("Melhor item atual na mochila: " + melhor.getNome());
    }

}
