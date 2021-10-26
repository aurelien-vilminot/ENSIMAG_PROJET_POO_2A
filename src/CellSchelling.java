import java.util.*;

public class CellSchelling extends Cell {
    private static int K=0;
    private static ArrayList<int[]> freeLodgment = new ArrayList<>();
    private static ArrayList<String> colors;

    public CellSchelling(int x, int y) {
        super(x, y);
        if (K == 0) {
            throw new IllegalArgumentException("Neighbors number must be defined with setK() static method");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colors number must be defined with setColors() static method");
        }

        int[] newElement = new int[]{this.x, this.y};
        if (!freeLodgment.isEmpty()) {
            int[] lastElement = freeLodgment.get(freeLodgment.size() - 1);
            if (!Arrays.equals(lastElement, newElement)) {
                freeLodgment.add(newElement);
            }
        } else {
            freeLodgment.add(newElement);
        }
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
            int[] newElement = new int[]{this.x, this.y};
            if (!freeLodgment.isEmpty()) {
                int[] lastElement = freeLodgment.get(freeLodgment.size() - 1);
                if (!Arrays.equals(lastElement, newElement)) {
                    freeLodgment.add(newElement);
                }
            } else {
                freeLodgment.add(newElement);
            }
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

        // Add unique color in the color array
        Random random = new Random();
        for (int i = 1 ; i < c ; i++) {
            String randomColor;
            do {
                randomColor = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            } while (colors.contains(randomColor) && !Objects.equals(randomColor, "#FFFFFF"));
            colors.add(randomColor);
        }
    }

    // TODO : trouver une structure adaptée (tuple ?)
    @Override
    public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
        int nbNeighboors = 0;
        ArrayList<int[]> coordStateArray = new ArrayList<int[]>();

        for (Cell c : neighbours) {
            if (c.state != this.state && c.state != 0) {
                ++nbNeighboors;
            }
        }

        if (nbNeighboors > K) {
            // Get the first free lodgement and remove it of freeLodgment static variable
            int[] newCellLodgementCoord = freeLodgment.get(0);
            freeLodgment.remove(0);

            freeLodgment.add(new int[]{this.x, this.y});

            coordStateArray.add(new int[]{this.x, this.y, 0});
            coordStateArray.add(new int[]{newCellLodgementCoord[0], newCellLodgementCoord[1], this.state});
        } else {
            coordStateArray.add(new int[]{this.x, this.y, this.state});
        }
        return coordStateArray;
    }

    public String getCellColor() {
        return colors.get(this.state);
    }
}
