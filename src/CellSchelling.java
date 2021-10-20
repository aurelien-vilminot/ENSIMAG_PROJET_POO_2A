import java.util.*;

public class CellSchelling extends Cell {
    private static int K=0;
    private static Queue<CellSchelling> freeLodgment = new LinkedList<CellSchelling>();
    private static ArrayList<String> colors;

    public CellSchelling(int x, int y) {
        super(x, y);
        if (K == 0) {
            throw new IllegalArgumentException("Neighbors number must be defined with setK() static method");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colors number must be defined with setColors() static method");
        }
        freeLodgment.add(this);
    }

    public CellSchelling(int x, int y, int state) {
        super(x, y, state);
        if (K == 0) {
            throw new IllegalArgumentException("Neighbors number must be defined with setK() static method");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colors number must be defined with setColors() static method");
        }

        if (state == 0) {
            freeLodgment.add(this);
        }
    }

    // User has to define K using this static method
    public static void setK(int k) {
        if (k > 8) {
            throw new IllegalArgumentException("Neighbors number must be lower than 9");
        }
        K = k;
    }

    public static void setColors(int c) {
        colors = new ArrayList<String>();
        // First color corresponds to a free lodgment
        colors.add("#FFFFFF");

        Random random = new Random();
        for (int i = 1 ; i < c ; i++) {
            String randomColor;
            do {
                randomColor = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            } while (colors.contains(randomColor));
            colors.add(randomColor);
        }
    }

    @Override
    public int nextState(ArrayList<Cell> neighbours) {
        int nbLivingN = 0;
        int newState;

        for (Cell c : neighbours) {
            if (c.state != this.state && c.state != 0) {
                nbLivingN++;
            }
        }

        if (nbLivingN > K) {
            CellSchelling newCellLodgement = freeLodgment.remove();
            freeLodgment.add(this);
            newCellLodgement.state = this.state;
            newState = 0;
        } else {
            newState = this.state;
        }
        return newState;
    }

    public String getCellColor() {
        return colors.get(this.state);
    }
}
