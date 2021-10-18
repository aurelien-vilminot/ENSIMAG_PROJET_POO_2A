import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLife {
	private Cell[][] cellArray;
	private Cell[][] initArray;
	private int height;
	private int width;
	private int step = 0;

	public GameOfLife(int height, int width, Cell... tab) {
		this.cellArray = new Cell[height][width];
		this.height = height;
		this.width = width;
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; i < width; j++) {
				this.cellArray[i][j] = new Cell(i, j);
				this.initArray[i][j] = new Cell(i, j);
			}
		}
		
		for (Cell c : tab) {
			Cell c0 = this.cellArray[c.x][c.y];
			c0.x = c.x;
			c0.y = c.y;
			c0.state = c.state;
		}
	}
	
	public Cell[][] getCellArray() {
		return this.cellArray;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getStep() {
		return step;
	}

	public void reInit() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; i < this.width; j++) {
				Cell c0 = this.initArray[i][j];
				this.cellArray[i][j] = new Cell(c0.x, c0.y, c0.state);
			}
		}
	}

	
	public ArrayList<Cell> getNeighbours(int x, int y) {
		ArrayList<Cell> nArray = new ArrayList<Cell>();
		// TODO
		return nArray;
	}
	
	public void step() {
		// TODO
		this.step++;
	}

	@Override
	public String toString() {
		// TODO
		return "TODO";
	}
}
