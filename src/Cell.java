import java.util.ArrayList;

/**
 * Creation of a cell of the grid
 * Reused for the game of Conway, Immigration and Schelling.
 */
public class Cell {
	protected int x;
	protected int y;
	protected int state = 0;

	/**
	 * First constructor of a cell
	 * @param x : coordinate on axis X.
	 * @param y : coordinate on axis Y.
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Overloading of the constructor of cell taking into account the state of the cell created
	 * @param x : coordinate on axis X.
	 * @param y : coordinate on axis Y.
	 * @param state : state of the cell create.
	 */
	public Cell(int x, int y, int state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Abstract method which gives the next state of the cells, depending on the game.
	 * @param neighbours : list the neighbours of the cells
	 * @throws IllegalCallerException : if the methods is called directly, it will throw an Exception because it's an
	 * abstract method
	 * @return : List of the new state of the cells after a step of the game.
	 */
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	/**
	 * Abstract method which return the color of the cell
	 * @throws IllegalCallerException : if the methods is called directly, it will throw an Exception because it's an
	 * abstract method
	 * @return : Color of the cell ( a String)
	 */
	public String getCellColor() {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	@Override
	public final String toString() {
		return "Cell [x=" + x + ", y=" + y + ", state=" + state + "]";
	}
}
