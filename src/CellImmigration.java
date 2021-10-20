import java.awt.*;
import java.util.ArrayList;

public class CellImmigration extends Cell {
	protected static int n = 0;
	private static String[] stateColor;

	public CellImmigration(int x, int y, int state) {
		super(x, y, state);
		if (n == 0) {
			throw new IllegalArgumentException("State number must be defined with setN() static method");
		}
	}

	// User has to define n using this static method
	public static void setN(int n0) {
		n = n0;
		stateColor = new String[n];
		int stepAlpha = 255 / n;
		for (int i = 0 ; i < n ; i++) {
			int scaledColor = 255 - (i*stepAlpha);
			stateColor[i] = String.format("#%02x%02x%02x", scaledColor, scaledColor, scaledColor);
		}
	}

	public int nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		int newState;
		int next = (this.state + 1) % (this.n);

		for (Cell c : neighbours) {
			if (c.state == next) {
				nbLivingN++;
			}
		}

		if (nbLivingN > 3) {
			// A cell in state k becomes k + 1 (mod n) if and only if it has 3+ neighbours
			// in state k + 1 (mod n)
			newState = next;
		} else {
			newState = this.state;
		}
		return newState;
	}

	public String getCellColor() {
		return stateColor[this.state];
	}
}
