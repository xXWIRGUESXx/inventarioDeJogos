package inventario;

public class GeradorDeItens {
    public enum MetodoRandom {
        LCG, XORSHIFT
    }

    private long semente;
    private int contadorPiedade = 0;
    private MetodoRandom metodo;

    // Construtor parametrizável
    public GeradorDeItens(long semente, MetodoRandom metodo) {
        this.semente = semente;
        this.metodo = metodo;
    }

    // Construtor padrão (usa LCG e seed do sistema)
    public GeradorDeItens() {
        this(System.currentTimeMillis(), MetodoRandom.LCG);
    }

    // Linear Congruential Generator (LCG)
    private double proximoRandomLCG() {
        semente = (semente * 25214903917L + 11L) & ((1L << 48) - 1);
        return (double) semente / (double) (1L << 48);
    }

    // Xorshift (32 bits)
    private double proximoRandomXorshift() {
        semente ^= (semente << 13);
        semente ^= (semente >>> 17);
        semente ^= (semente << 5);
        // Normaliza para [0,1)
        return (double) (semente & 0x7FFFFFFFFFFFFFFFL) / (double) Long.MAX_VALUE;
    }

    // Método principal de random parametrizado
    private double proximoRandom() {
        switch (metodo) {
            case XORSHIFT:
                return proximoRandomXorshift();
            case LCG:
            default:
                return proximoRandomLCG();
        }
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