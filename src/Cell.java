import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class which represents the skeleton for a cell.
 */
public abstract class Cell {
	protected int x;
	protected int y;
	protected int state = 0;

	/**
	 * Constructor of cell.
	 * @param x Coordinate on x-axis.
	 * @param y Coordinate on y-axis.
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Constructor of cell with initial state.
	 * @param x Coordinate on x-axis.
	 * @param y Coordinate on y-axis.
	 * @param state State of the cell created.
	 */
	public Cell(int x, int y, int state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

    public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/**
	 * Abstract method which gives the next state of the cell, depending on the game.
	 * @param neighbours List of the cell's neighbours.
	 * @return List of new states of the cells after a step of the game.
	 */
	public abstract ArrayList<int[]> nextState(ArrayList<Cell> neighbours);

	/**
	 * Abstract method which return the color of the cell.
	 * @return Color of the cell.
	 */
	public abstract Color getCellColor();

	@Override
	public final String toString() {
		return "Cell [x=" + this.x + ", y=" + this.y + ", state=" + this.state + "]";
	}
}
