import java.util.ArrayList;

public class Cell {
	public int x;
	public int y;
	public int state = 0;

	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Cell(int x, int y, int state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public int nextState(ArrayList<Cell> neighbours) {
		System.out.println("!!! nextState: YOU SHOULD NOT BE USING ME !!!");
		return 0;
	}

	public String getCellColor() {
		System.out.println("!!! getCellColor: YOU SHOULD NOT BE USING ME !!!");
		return "";
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", state=" + state + "]";
	}
}
