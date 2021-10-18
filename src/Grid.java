import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class Grid {
	private Cell[][] cellArray;
	private Cell[][] initArray;
	private int height;
	private int width;
	private int stepNumber = 0;

	public Grid(int height, int width, String gameName, Cell... tab) {
		this.cellArray = new Cell[height][width];
		this.initArray = new Cell[height][width];
		this.height = height;
		this.width = width;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				switch (gameName) {
					case "Conway":
						this.cellArray[i][j] = new CellConway(i, j);
						this.initArray[i][j] = new CellConway(i, j);
						break;
					case "Immigration":
						this.cellArray[i][j] = new CellImmigration(i, j, 0);
						this.initArray[i][j] = new CellImmigration(i, j, 0);
						break;
					default:
						// TODO: Exception
						System.out.println("!!! THE GAME DOESN'T EXIST !!!");
				}
			}
		}
		for (Cell c : tab) {
			Cell c0 = this.cellArray[c.x][c.y];
			c0.state = c.state;
		}
	}
	
	public Cell[][] getCellArray() {
		return this.cellArray;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getStep() {
		return this.stepNumber;
	}

	public void reInit() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				Cell c0 = this.initArray[i][j];
				this.cellArray[i][j] = new Cell(c0.x, c0.y, c0.state);
			}
		}
	}

	
	public ArrayList<Cell> getNeighbours(int x, int y) {
		ArrayList<Cell> nArray = new ArrayList<Cell>();
		if (x == 0){
			if (y == 0) {
				nArray.add(cellArray[x][y+1]);
				nArray.add(cellArray[x+1][y-1]);
				nArray.add(cellArray[x+1][y]);
				nArray.add(cellArray[x+1][y+1]);
			}
		}
		// TODO
		return nArray;
	}
	
	public void step() {
		int[][] stateArray = new int[this.height][this.width];

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				stateArray[i][j] = this.cellArray[i][j].nextState(getNeighbours(i, j));
			}
		}

		// Change states after
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.cellArray[i][j].state = stateArray[i][j];
			}
		}
		this.stepNumber++;
	}

	public String getCellColor(int x, int y) {
			return this.cellArray[y][x].getCellColor();
	}

	@Override
	public String toString() {
		String res = "";
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				if (this.cellArray[i][j].state == 0) {
					res += "O";
				} else {
					res += "X";
				}
			}
			if (i != this.height-1) { res += "\n"; }
		}
		return "TODO";
	}

}
