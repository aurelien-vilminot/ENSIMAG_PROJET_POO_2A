import java.awt.*;
import java.util.ArrayList;

/**
 * Creation of a cell of the grid
 * Reused for the game of Conway, Immigration and Schelling.
 */
public abstract class Cell {
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
	 * @return : List of the new state of the cells after a step of the game.
	 */
	public abstract ArrayList<int[]> nextState(ArrayList<Cell> neighbours);

	/**
	 * Abstract method which return the color of the cell
	 * @return : Color of the cell
	 */
	public abstract Color getCellColor();

	@Override
	public final String toString() {
		return "Cell [x=" + this.x + ", y=" + this.y + ", state=" + this.state + "]";
	}
}
