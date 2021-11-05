import java.util.ArrayList;

/**
 * Class of cells corresponding to the game of 'Immigration', inherited from Cells.
 */
public class CellImmigration extends Cell {
	private static int n = 0;
	private static String[] stateColor;

	/**
	 * Constructor of a cell for to the game of 'Immigration', using one of the constructor
	 * of the mother class.
	 * @param x : coordinate on axis X.
	 * @param y : coordinate on axis Y.
	 * @param state : state of the cell create.
	 */
	public CellImmigration(int x, int y, int state) {
		super(x, y, state);
		if (n == 0) {
			throw new IllegalArgumentException("State number must be defined with setN() static method");
		}
		if (state >= n) {
			throw new IllegalArgumentException("State number must be inferior to n");
		}
	}



	/**
	 * User has to define n using this static method.
	 * @param n0 : number of color of the game.
	 */
	public static void setN(int n0) {
		n = n0;

		// Define colors depending steps
		stateColor = new String[n];
		int stepAlpha = 255 / (n-1);
		for (int i = 0 ; i < n ; i++) {
			int scaledColor = 255 - (i*stepAlpha);
			stateColor[i] = String.format("#%02x%02x%02x", scaledColor, scaledColor, scaledColor);
		}
	}


	@Override
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int next = (this.state + 1) % (n);
		ArrayList<int[]> coordStateArray = new ArrayList<int[]>();

		for (Cell c : neighbours) {
			if (c.state == next) {
				nbLivingN++;
			}
		}

		if (nbLivingN >= 3) {
			// A cell in state k becomes k + 1 (mod n) if and only if it has 3+ neighbours
			// in state k + 1 (mod n)
			coordStateArray.add(new int[]{this.x, this.y, next});
		}
		return coordStateArray;
	}

	@Override
	public String getCellColor() {
		return stateColor[this.state];
	}
}
