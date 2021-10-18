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

    public GridSimulator(GUISimulator gui, String gameName, int cellSize, Cell... tab) {
        this.gui = gui;
        this.width = gui.getPanelWidth();
        this.height = gui.getPanelHeight();

        this.cellSize = cellSize;
        this.columCell= this.width / this.cellSize;
        this.linesCell = this.height / this.cellSize;

        this.grid = new Grid(this.linesCell, this.columCell, gameName, tab);

    }

    public void draw() {
        // ICI CELL N'EST PLUS UNE CELLCONWAY
        System.out.println(this.grid.getCellArray()[0][0] instanceof CellConway);

        Cell[][] cellArray = this.grid.getCellArray();
        int widthPadding = 0;
        int heightPadding = 0;

        for (int i = 0; i < this.linesCell; i++) {
            for (int j = 0; j < this.columCell; j++) {
                String cellColor = cellArray[i][j].getCellColor();
                gui.addGraphicalElement(new Rectangle(i + widthPadding, j+heightPadding, Color.decode("#0000ff"), Color.decode(cellColor), this.cellSize));
            }
            widthPadding+=10;
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
        System.out.println("Je suis restart");
        this.grid.reInit();
        gui.reset();
        this.draw();
    }
}
