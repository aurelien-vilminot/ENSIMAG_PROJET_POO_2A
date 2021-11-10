import java.awt.*;
import java.util.ArrayList;

/**
 * Class of cells corresponding to the game of life, inherited from Cells.
 */
public class CellConway extends Cell {
	private final Color COLOR_STATE_ALIVE = Color.decode("#03A5FC");
	private final Color COLOR_STATE_DEAD = Color.decode("#F2F2F2");

	/**
	 * Constructor of life cell.
	 * @see Cell for params specifications.
	 */
	public CellConway(int x, int y) {
		super(x, y);
	}

	/**
	 * Constructor of life cell with state specification.
	 * @see Cell for params specifications.
	 */
	public CellConway(int x, int y, int state) {
		super(x, y, state);
	}

	/**
	 * Give the next state of the cell using game of life rules.
	 * @param neighbours List of the cell's neighbours.
	 * @return List which represents the cell coordinates and its new state. The list is empty if the state
	 * 		   doesn't change.
	 */
	@Override
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int newState;
		ArrayList<int[]> coordStateArray = new ArrayList<>();

		for (Cell c : neighbours) {
			if (c.state == 1) {
				nbLivingN++;
			}
		}
		if (this.state == 1 && (nbLivingN == 2 || nbLivingN == 3)) {
			// Rule 1: Any live cell with two or three live neighbours survives
			newState = 1;
		} else if (this.state == 0 && nbLivingN == 3) {
			// Rule 2: Any dead cell with three live neighbours becomes a live cell
			newState = 1;
		} else {
			// Rule 3: All others lived cells die in the next generation. Similarly, all other
			// dead cells stay dead
			newState = 0;
		}
		if (this.state != newState) {
			coordStateArray.add(new int[]{this.x, this.y, newState});
		}
		return coordStateArray;
	}

	/**
	 * Return the color of the cell.
	 * @return The corresponding color.
	 */
	@Override
	public Color getCellColor() {
		if (this.state == 0) {
			return this.COLOR_STATE_DEAD;
		} else {
			return this.COLOR_STATE_ALIVE;
		}
	}
}
