import java.util.ArrayList;

/**
 * Class of cells corresponding to the game of Conway, inherited from Cells.
 */
public class CellConway extends Cell {
	/**
	 * 1st constructor of a cell for to the game of life on Conway, using one of the constructor
	 * of the mother class.
	 * @param x : coordonate on axis X.
	 * @param y : coordonate on axis Y.
	 */
	public CellConway(int x, int y) {
		super(x, y);
	}

	/**
	 * Another constructor overload of a cell for to the game of life on Conway,
	 * using one of the constructor of the mother class.
	 * @param x : coordonate on axis X.
	 * @param y : coordonate on axis Y.
	 * @param state : state of the cell create.
	 */
	public CellConway(int x, int y, int state) {
		super(x, y, state);
	}


	@Override
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int newState;
		ArrayList<int[]> coordStateArray = new ArrayList<int[]>();

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
			// Rule 3: All other live cells die in the next generation. Similarly, all other
			// dead cells stay dead
			newState = 0;
		}
		if (this.state != newState) {
			coordStateArray.add(new int[]{this.x, this.y, newState});
		}
		return coordStateArray;
	}

	@Override
	public String getCellColor() {
		if (this.state == 0) {
			return "#F2F2F2";
		} else {
			return "#03A5FC";
		}
	}
}
