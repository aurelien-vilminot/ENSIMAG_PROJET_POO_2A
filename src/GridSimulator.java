import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/**
 * Management of the grid display common to all games.
 * Implements the interface Simulable which cares of the display.
 */
public class GridSimulator extends Simulator {
	private static final String BORDERCOLOR = "#FCBACB";
	private int cellSize;
	private int linesCell;
	private int columCell;

	private String gameName;

	/**
	 * Constructor of an object which will display the grid, using the GUISimulator
	 * @param gui : an instance of GUISimulator
	 * @param gameName : name of the game.
	 * @param cellSize : size of the cell
	 * @param cells : cells used to fill the grid.
	 */
	public GridSimulator(GUISimulator gui, String gameName, int cellSize, Cell... cells) {
		super(gui);

		// Calculate the colums and lines number depending on cell size
		this.cellSize = cellSize;
		this.columCell = this.gui.getPanelWidth() / this.cellSize;
		this.linesCell = this.gui.getPanelHeight() / this.cellSize;

		// Check dimensions
		if (this.cellSize < 1 || this.columCell < 1 || this.linesCell < 1) {
			throw new IllegalArgumentException("You must enter correct sizes for cells and window");
		}

		// Check if there are init cells into cells
		if (cells.length == 0) {
			throw new IllegalArgumentException("You must init some cells to play");
		}

		// Init the game
		this.gameName = gameName;
		this.backend = new Grid(this.linesCell, this.columCell, this.gameName, cells);
	}

	/**
	 * Draw the cells with different colors.
	 */
	public void draw() {
		Grid grid = (Grid) this.backend;
		Cell[][] cellArray = grid.getCellArray();
		int padding = (this.cellSize / 2);

		for (int i = 0; i < this.linesCell; i++) {
			for (int j = 0; j < this.columCell; j++) {
				String cellColor = cellArray[i][j].getCellColor();
				gui.addGraphicalElement(new Rectangle(i * this.cellSize + padding, j * this.cellSize + padding,
						Color.decode(BORDERCOLOR), Color.decode(cellColor), this.cellSize));
			}
		}
	}
}
