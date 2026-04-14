public class Item {
    private String nome;
    private int raridade;

    public Item(String nome, int raridade) {
        this.nome = nome;
        this.raridade = raridade;
    }

    public String getNome() {
        return nome;
    }

    public int getRaridade() {
        return raridade;
    }
}
