import java.awt.*;
import java.util.ArrayList;

/**
 * Class of cells corresponding to the game of Immigration, inherited from Cells.
 */
public class CellImmigration extends Cell {
	private static int n = 0;
	private static Color[] stateColor;

	/**
	 * Constructor of Immigration game cell.
	 * @see Cell for params specifications.
	 * @throws IllegalArgumentException If the state number is not defined or if the state of the cell is higher than n.
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
	 * User has to define the attribute n using this static method. It will create a nuance of grey colors.
	 * @param n0 Number of colors.
	 */
	public static void setN(int n0) {
		n = n0;

		// Define colors depending steps
		stateColor = new Color[n];
		int stepAlpha = 255 / (n-1);
		for (int i = 0 ; i < n ; i++) {
			int scaledColor = 255 - (i*stepAlpha);
			stateColor[i] = Color.decode(String.format("#%02x%02x%02x", scaledColor, scaledColor, scaledColor));
		}
	}

	/**
	 * Give the next state of the cell using Immigration game rules.
	 * @param neighbours List of the cell's neighbours.
	 * @return List which represents the cell coordinates and its new state. The list is empty if the state
	 * 		   doesn't change.
	 */
	@Override
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int next = (this.state + 1) % (n);
		ArrayList<int[]> coordStateArray = new ArrayList<>();

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

	/**
	 * Return the color linked to the state.
	 * @return The corresponding color.
	 */
	@Override
	public Color getCellColor() {
		return stateColor[this.state];
	}
}
