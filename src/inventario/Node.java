
package inventario;

public class Node {
    Item item;
    Node proximo;

    public Node(Item item) {
        this.item = item;
        this.proximo = null;
    }

}
