import gui.GUISimulator;
import gui.Oval;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

public class GridSimulator implements Simulable {
	private Grid grid;
	private GUISimulator gui;
	private int width;
	private int height;

	private int nbCellule;
	private int cellSize;

	private int linesCell;
	private int columCell;

	private String gameName;

	public GridSimulator(GUISimulator gui, String gameName, int cellSize, Cell... tab) {
		this.gui = gui;
		this.width = gui.getPanelWidth();
		this.height = gui.getPanelHeight();

		this.cellSize = cellSize;
		this.columCell = this.width / this.cellSize;
		this.linesCell = this.height / this.cellSize;

		this.gameName = gameName;

		this.grid = new Grid(this.linesCell, this.columCell, this.gameName, tab);

	}

	public void draw() {
		Cell[][] cellArray = this.grid.getCellArray();
		int padding = (this.cellSize / 2);

		for (int i = 0; i < this.linesCell; i++) {
			for (int j = 0; j < this.columCell; j++) {
				String cellColor = cellArray[i][j].getCellColor();
				gui.addGraphicalElement(new Rectangle(j * this.cellSize + padding, i * this.cellSize + padding,
						Color.decode("#0000ff"), Color.decode(cellColor), this.cellSize));
			}
		}
	}

	@Override
	public void next() {
		gui.reset();
		this.grid.step();
		this.draw();
	}

	@Override
	public void restart() {
		this.grid.reInit(this.gameName);
		gui.reset();
		this.draw();
	}
}
