import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;

/*
Gestion de l'affichage de la grille commune à tous les jeux
*/
public class GridSimulator implements Simulable {
	private static final String BORDERCOLOR = "#FCBACB";

	private GUISimulator gui;

	private Grid grid;
	private int cellSize;
	private int linesCell;
	private int columCell;

	private String gameName;

	/*
	Construction de l'affichage de la grille grâce à la librairie GUI.
	
	*/
	public GridSimulator(GUISimulator gui, String gameName, int cellSize, Cell... cells) {
		this.gui = gui;

		// Calculate the colums and lines number depending on cell size
		this.cellSize = cellSize;
		this.columCell = this.gui.getPanelWidth() / this.cellSize;
		this.linesCell = this.gui.getPanelHeight() / this.cellSize;

		// Check dimensions
		if (this.cellSize < 1 || this.columCell < 1 || this.linesCell < 1) {
			throw new IllegalArgumentException("You must enter correct sizes for cells and window");
		}

		// Init the game
		// The player must init cells using "cells" parameter
		this.gameName = gameName;
		this.grid = new Grid(this.linesCell, this.columCell, this.gameName, cells);
	}

	public void draw() {
		Cell[][] cellArray = this.grid.getCellArray();
		int padding = (this.cellSize / 2);

		for (int i = 0; i < this.linesCell; i++) {
			for (int j = 0; j < this.columCell; j++) {
				String cellColor = cellArray[i][j].getCellColor();
				gui.addGraphicalElement(new Rectangle(i * this.cellSize + padding, j * this.cellSize + padding,
						Color.decode(BORDERCOLOR), Color.decode(cellColor), this.cellSize));
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
