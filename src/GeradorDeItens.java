public class GeradorDeItens {
    private long semente = System.currentTimeMillis();
    private int contadorPiedade = 0;

    private double proximoRandom() {
        semente = (semente * 25214903917L + 11L) & ((1L << 48) - 1);
        return (double) semente / (double) (1L << 48);
    }

    public Item gerarDrop() {
        double sorte = proximoRandom();

        double bonusPiedade = contadorPiedade * 0.01;
        double chanceFinal = sorte + bonusPiedade;

        if (chanceFinal > 0.99) {
            contadorPiedade = 0;
            return new Item("Item Mítico", 5);
        }

        if (chanceFinal > 0.94) {
            contadorPiedade = 0;
            return new Item("Item Lendário", 4);
        }

        if (chanceFinal > 0.80) {
            return new Item("Item Raro", 3);
        }

        if (chanceFinal > 0.40) {
            contadorPiedade++;
            return new Item("Item Comum", 2);
        }

        contadorPiedade++;
        return new Item("Item Inicial", 1);
    }
}