public class Cell {
	public int x;
	public int y;
	public int alive = 0;
	
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Cell(int x, int y, int alive) {
		this.x = x;
		this.y = y;
		if (alive != 0) {
			this.alive = 1;			
		} else {
			this.alive = 0;
		}
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", alive=" + alive + "]";
	}
}
