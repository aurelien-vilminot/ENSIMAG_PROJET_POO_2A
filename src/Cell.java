import java.util.ArrayList;

/**
 * Creation of a celule of the grid
 * Reused for the game of Conway, Immigration and Schelling.
 */
public class Cell {
	protected int x;
	protected int y;
	protected int state = 0;

	/**
	 * First constructor of a cell
	 * @param x : coordonate on axis X.
	 * @param y : coordonate on axis Y.
	 */
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * overloading of the constructor of cell takink into account the state of the cell create
	 * @param x : coordonate on axis X.
	 * @param y : coordonate on axis Y.
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

	/*
        Donne le prochain état de la cellule sur laquelle on appelle la méthode, méthode créee pour être override par les classes héritées
        ArrayList<Cell> neighbours = la liste de cellule des voisins
         */

	/**
	 * Abstract method which gives the next state of the cells, depending of the game.
	 * @param neighbours : list the the neighbours of the cells
	 * @throws : if the methods is called directly, it will throw an Exception because it's an
	 * abstract method
	 * @return : List of the new state of the cells after a step of the game.
	 */
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	/**
	 * Abstract method which return the color of the cell
	 * @throws : if the methods is called directly, it will throw an Exception because it's an
	 * abstract method
	 * @return : Color of the cell ( a String)
	 */
	public String getCellColor() {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", state=" + state + "]";
	}
}
