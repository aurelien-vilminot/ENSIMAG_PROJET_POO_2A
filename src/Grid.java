import java.util.ArrayList;
import java.lang.Math;

/**
 * Management of the grid backend common to all cell games.
 * Implements the interface Backend.
 */
public class Grid implements Backend {
	private Cell[][] cellArray;
	private int[][] initStatesArray;
	private int height;
	private int width;

	/**
	 * Constructor of a grid containing specific cells of the game choose.
	 * @param height Height of the grid.
	 * @param width Width of the grid.
	 * @param gameName Choice of the game.
	 * @param cells Cells used in the grid.
	 */
	public Grid(int height, int width, String gameName, Cell... cells) {
		this.cellArray = new Cell[height][width];
		this.initStatesArray = new int[height][width];
		this.height = height;
		this.width = width;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.initStatesArray[i][j] = 0;
				switch (gameName) {
					case "Conway" -> this.cellArray[i][j] = new CellConway(i, j, 0);
					case "Immigration" -> this.cellArray[i][j] = new CellImmigration(i, j, 0);
					case "Schelling" -> this.cellArray[i][j] = new CellSchelling(i, j, 0);
					default -> throw new IllegalArgumentException("!!! THE GAME DOESN'T EXIST !!!");
				}
			}
		}
		for (Cell c : cells) {
			if (c.x > this.width - 1 || c.y > this.height - 1) {
				throw new IllegalArgumentException("Init cells must be placed into the grid");
			}
			this.cellArray[c.x][c.y].setState(c.getState());
			this.initStatesArray[c.x][c.y] = c.getState();
		}
	}

	public Cell[][] getCellArray() {
		return this.cellArray;
	}


	/**
	 * Reinitialize the grid by changing the state of the cells.
	 */
	public void reInit() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				this.cellArray[i][j].setState(this.initStatesArray[i][j]);
			}
		}
	}

	/**
	 * Gives the list of neighbours of a cell.
	 * @param x Position of the cell on the grid on the axis X.
	 * @param y Position of the cell on the grid on the axis Y.
	 * @return The list of neighbours of a given cell, with interconnected edges.
	 */
	public ArrayList<Cell> getNeighbours(int x, int y) {
		ArrayList<Cell> nArray = new ArrayList<>();
		int w = this.width - 1;
		int h = this.height - 1;
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][Math.floorMod(y-1, w)]); // topLeft
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][y]); // top
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][Math.floorMod(y+1, w)]); // topRight
		nArray.add(this.cellArray[x][Math.floorMod(y+1, w)]); // right
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][Math.floorMod(y+1, w)]); // bottomRight
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][y]); // bottom
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][Math.floorMod(y-1, w)]); // bottomLeft
		nArray.add(this.cellArray[x][Math.floorMod(y-1, w)]); // left
		return nArray;
	}

	/**
	 * Step forward the current game, updating the states of the different cells.
     * @param type Type of the cell. Can be null.
	*/
	public void step(String type) {
		ArrayList<int[]> stateList = new ArrayList<>();

		// For each cell, add to stateList the modified states
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				// coordStates[0][0] = x coord of the first cell
				// coordStates[0][1] = y coord of the first cell
				// coordStates[0][2] = state of the first cell
				ArrayList<int[]> coordStates = this.cellArray[i][j].nextState(getNeighbours(i, j));
				stateList.addAll(coordStates);
			}
		}

		// Change states after
		for (int[] cell : stateList) {
			int xCoord = cell[0];
			int yCoord = cell[1];
			int state = cell[2];
			this.cellArray[xCoord][yCoord].setState(state);
		}
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				res.append(this.cellArray[j][i].getState());
			}
			if (i != this.height - 1) {
				res.append("\n");
			}
		}
		return res.toString();
	}
}
