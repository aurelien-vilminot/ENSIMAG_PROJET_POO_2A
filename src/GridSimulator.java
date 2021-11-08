import gui.GUISimulator;
import gui.Rectangle;

import java.awt.*;

/**
 * Management of the grid display common to all games.
 * Implements the interface Simulable which cares of the display.
 */
public class GridSimulator extends Simulator {
	private static final Color borderColor = Color.decode("#FCBACB");
	private int cellSize;
	private int linesCell;
	private int columnCell;

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
		this.eventManager.addEvent(new SimulatorEvent(0, this, 1, null));

		// Calculate the columns and lines number depending on cell size
		this.cellSize = cellSize;
		this.columnCell = this.gui.getPanelWidth() / this.cellSize;
		this.linesCell = this.gui.getPanelHeight() / this.cellSize;

		// Check dimensions
		if (this.cellSize < 1 || this.columnCell < 1 || this.linesCell < 1) {
			throw new IllegalArgumentException("You must enter correct sizes for cells and window");
		}

		// Check if there are init cells into cells
		if (cells.length == 0) {
			throw new IllegalArgumentException("You must init some cells to play");
		}

		// Init the game
		this.gameName = gameName;
		this.backend = new Grid(this.linesCell, this.columnCell, this.gameName, cells);
	}

	/**
	 * Draw the cells with different colors.
	 */
	public void draw() {
		Grid grid = (Grid) this.backend;
		Cell[][] cellArray = grid.getCellArray();
		int padding = (this.cellSize / 2);

		for (int i = 0; i < this.linesCell; i++) {
			for (int j = 0; j < this.columnCell; j++) {
				gui.addGraphicalElement(new Rectangle(i * this.cellSize + padding, j * this.cellSize + padding,
						borderColor, cellArray[i][j].getCellColor(), this.cellSize));
			}
		}
	}
}
