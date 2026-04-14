public class TabelaHash {

    private Node[] tabela;
    private int tamanho;

    public TabelaHash(int capacidade) {
        this.tabela = new Node[capacidade];
        this.tamanho = capacidade;

    }
    //tanto faz se eh privado ou nao
    private int calcularIndice(String chave) {
        int hash = 0;
        for(int i = 0; i < chave.length(); i++) {
            hash = (31 * hash + chave.charAt(i));

        }
        return Math.abs(hash) % tamanho;
    }

    public void inserir(Item item) {
        int indice = calcularIndice(item.getNome());
        Node novo = new Node(item);
        if (tabela[indice] != null) {
            novo.proximo = tabela[indice];
        }
        tabela[indice] = novo;
    }
    public Item buscar(String nome) {
        int indice = calcularIndice(nome);
        Node atual = tabela[indice];
        while (atual != null) {
            if (atual.item.getNome().equals(nome))
                return atual.item;
            atual = atual.proximo;
        }
        return null;
    }

}
