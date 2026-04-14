public class MaxHeap {
    private Item[] heap;
    private int quantidade;

    public int getQuantidade() {
        return quantidade;
    }

    public MaxHeap(int capacidade) {
        this.heap = new Item[capacidade];
        this.quantidade = 0;
    }

    public void inserir(Item item) {
        heap[quantidade] = item;
        subir(quantidade);
        quantidade++;
    }

    private void subir(int i) {
        while (i > 0 && heap[i].getRaridade() > heap[(i - 1) / 2].getRaridade()) {
            trocar(i, (i - 1) / 2);
            i = (i - 1) / 2;
        }
    }

    private void trocar(int i, int j) {
        Item temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public Item maisRaro() {
        if (quantidade == 0) {
            return null;
        }
        return heap[0];
    }
    private void descer(int i) {
        int maior = i;
        int esquerda = 2 * i + 1;
        int direita = 2 * i + 2;

        if (esquerda < quantidade && heap[esquerda].getRaridade() > heap[maior].getRaridade()) {
            maior = esquerda;
        }

        if (direita < quantidade && heap[direita].getRaridade() > heap[maior].getRaridade()) {
            maior = direita;
        }

        if (maior != i) {
            trocar(i, maior);
            descer(maior);
        }
    }

    public Item extrairMax() {
        if (quantidade == 0)
            return null;

        Item topo = heap[0];
        heap[0] = heap[quantidade - 1];
        quantidade--;
        descer(0);
        return topo;
    }
}
