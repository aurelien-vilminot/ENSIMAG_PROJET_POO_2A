import java.util.ArrayList;

public class CellImmigration extends Cell {
	protected static int n = 0;

	public CellImmigration(int x, int y, int state) {
		super(x, y, state);
		if (n == 0) {
			System.out.println("!!! DEFINE n WITH setN STATIC METHOD !!!");
		}
	}

	// User has to define n using this static method
	public static void setN(int n0) {
		n = n0;
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
		// TODO
		return "";
	}
}
