public class Personagem {
    private String nome;
    private String classe;
    private SistemaInventario inventario;

    public Personagem(String nome, String classe) {
        this.nome = nome;
        this.classe = classe;
        this.inventario = new SistemaInventario();
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
