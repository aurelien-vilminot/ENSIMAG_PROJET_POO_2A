import java.awt.*;
import java.util.*;

/**
 * Class of cells corresponding to the game of Schelling, inherited from Cells.
 */
public class CellSchelling extends Cell {
    /**
     * The number of different colors of lodgment.
     */
    private static int k = 0;

    /**
     * List of free lodgments.
     */
    private static ArrayList<int[]> freeLodgment = new ArrayList<>();

    /**
     * List of colors, ordered by state.
     */
    private static ArrayList<Color> colors = new ArrayList<>();

    /**
     * Constructor of Schelling game cell. State is not specified, the cell is considered as free,
     * so added the list of free lodgement.
     * @see Cell for params specifications.
     * @throws IllegalArgumentException If the number K of the class is 0 or if the list of color is NULL.
     */
    public CellSchelling(int x, int y) {
        super(x, y);
        if (k == 0) {
            throw new IllegalArgumentException("Neighbors number must be defined with setK() static method");
        }
        if (colors == null) {
            throw new IllegalArgumentException("Colors number must be defined with setColors() static method");
        }

        int[] newElement = new int[]{this.x, this.y};
        freeLodgment.add(newElement);
    }

    /**
     * Constructor of Schelling game cell with state specification. If the state is 0, we add the lodgment to the list
     * of free lodgement.
     * @see Cell for params specifications.
     * @throws IllegalArgumentException If the number K of the class is 0 or if the list of color is NULL.
     */
    public CellSchelling(int x, int y, int state) {
        super(x, y, state);
        if (k == 0) {
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

    /**
     * Update the cell state by adding or removing it from freeLodgment list if necessary.
     * @param state The new state of the cell.
     */
    @Override
    public void setState(int state) {
        if (state != this.state) {
            // Remove the cell from freeLodgment if it's contained on it
            freeLodgment.removeIf(freeCell -> freeCell[0] == this.x && freeCell[1] == this.y);
            if (state == 0) {
                freeLodgment.add(new int[]{this.x, this.y});
            }
        }
        super.setState(state);
    }

    /**
     * User has to define K, the number of different colors of lodgment.
     * @param k Number of color of the game.
     * @throws IllegalCallerException If the number is strictly superior to 8.
     */
    public static void setK(int k) {
        if (k > 8) {
            throw new IllegalArgumentException("Neighbors number must be lower than 9");
        }
        CellSchelling.k = k;
    }

    /**
     * Generate random and unique colors and add them to the color static list.
     * @param c Number of colors to be generated.
     */
    public static void setColors(int c) {
        // First color corresponds to a free lodgment
        colors.add(Color.decode("#FFFFFF"));

        // Add unique color in the color array
        Random random = new Random();
        for (int i = 1 ; i < c ; i++) {
            String randomColor;
            do {
                randomColor = String.format("#%02x%02x%02x", random.nextInt(256), random.nextInt(256), random.nextInt(256));
            } while (colors.contains(Color.decode(randomColor)) && !Objects.equals(randomColor, "#FFFFFF"));
            colors.add(Color.decode(randomColor));
        }
    }

    /**
     * Give the next state of the cell using Schelling game rules.
     * @param neighbours List of the cell's neighbours.
     * @return List which represents the cells coordinates and their new state. The list is empty if the state
     * 		   doesn't change.
     * @throws IndexOutOfBoundsException If there are no more free lodgment available.
     */
    @Override
    public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
        int nbNeighboors = 0;
        ArrayList<int[]> coordStateArray = new ArrayList<>();

        if (this.state == 0) {
            // If the cell is a freeLodgement, it will don't move anyway
            return coordStateArray;
        }

        for (Cell c : neighbours) {
            if (c.state != this.state && c.state != 0) {
                ++nbNeighboors;
            }
        }

        if (nbNeighboors > k) {
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

    /**
     * Return the color linked to the state.
     * @return The corresponding color.
     */
    @Override
    public Color getCellColor() {
        return colors.get(this.state);
    }
}
