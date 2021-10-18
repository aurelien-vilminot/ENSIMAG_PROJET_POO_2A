import java.util.ArrayList;

public class CellConway extends Cell {
	public CellConway(int x, int y) {
		super(x, y);
	}

	public CellConway(int x, int y, int state) {
		super(x, y, state);
	}

	@Override
	public int nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int newState;

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
		return newState;
	}

	public String getCellColor() {
		if (this.state == 0) {
			return "#F2F2F2";
		} else {
			return "#03A5FC";
		}
	}
}
