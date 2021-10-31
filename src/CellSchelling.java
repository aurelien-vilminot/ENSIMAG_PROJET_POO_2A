import java.util.*;

/**
 * Class of cells corresponding to the game of Schelling, inherited from Cells.
 */
public class CellSchelling extends Cell {
    private static int K=0;
    private static ArrayList<int[]> freeLodgment = new ArrayList<>();
    private static ArrayList<String> colors;

    /**
     * 1st constructor of a cell for to the game of Schelling, using one of the constructor
     * of the mother class.
     * State is not specified, the cell is considered as free, so add the the list of free lodgement.
     * @param x : coordonate on axis X.
     * @param y : coordonate on axis Y.
     * @throws Exception if the number K of the class is 0 or if the list of color is Null.
     */
    public CellSchelling(int x, int y) {
        super(x, y);
        if (K == 0) {
            throw new IllegalArgumentException("Neighbors number must be defined with setK() static method");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colors number must be defined with setColors() static method");
        }

        int[] newElement = new int[]{this.x, this.y};
        freeLodgment.add(newElement);
    }

    /**
     * 1st constructor of a cell for to the game of Schelling, using one of the constructor
     * of the mother class.
     * @param x : coordonate on axis X.
     * @param y : coordonate on axis Y.
     * @param state : state of the cell create, if the state is 0, we add the lodgment to the list
     *              of free lodgement.
     * @throws Exception if the number K of the class is 0 or if the list of color is Null.
     */
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
            freeLodgment.add(newElement);
        }
    }

    @Override
    public void setState(int state) {
        if (state != this.state) {
            if (state == 0) {
                freeLodgment.add(new int[]{this.x, this.y});
            } else {
                freeLodgment.removeIf(freeCell -> freeCell[0] == this.x && freeCell[1] == this.y);
            }

        }
        super.setState(state);
    }

    /**
     * User has to define K the number of different colors of lodgemet.
     * @param k : number of color of the game.
     * @throws exception if the number is strictly superior to 8.
     */
    public static void setK(int k) {
        if (k > 8) {
            throw new IllegalArgumentException("Neighbors number must be lower than 9");
        }
        K = k;
    }

    /**
     * Define the colors of the different lodgements and fill a list of the colors.
     * @param c : number of colors.
     */
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
            try {
                // Get the first free lodgement and remove it of freeLodgment static variable
                int[] newCellLodgementCoord = freeLodgment.get(0);
                freeLodgment.remove(0);

                // Add the current cell to freeLodgment
                freeLodgment.add(new int[]{this.x, this.y});

                coordStateArray.add(new int[]{this.x, this.y, 0});
                coordStateArray.add(new int[]{newCellLodgementCoord[0], newCellLodgementCoord[1], this.state});
            } catch (IndexOutOfBoundsException exception) {
                System.out.println("There are no more free lodgment available");
            }
        }
        return coordStateArray;
    }

    @Override
    public String getCellColor() {
        return colors.get(this.state);
    }
}
