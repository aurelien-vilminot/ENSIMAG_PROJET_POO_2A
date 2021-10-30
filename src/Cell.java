import java.util.ArrayList;

public class Cell {
	protected int x;
	protected int y;
	protected int state = 0;

	/* 
	Construction d'une cellule non spécifiée
	*/
	public Cell(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* 
	Construction surchargée d'une cellule non spécifiée
	*/
	public Cell(int x, int y, int state) {
		this.x = x;
		this.y = y;
		this.state = state;
	}

	public int getX() {
		return this.x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getState() {
		return this.state;
	}

	public void setState(int state) {
		this.state = state;
	}

	/*
        Donne le prochain état de la cellule sur laquelle on appelle la méthode, méthode créee pour être override par les classes héritées
        ArrayList<Cell> neighbours = la liste de cellule des voisins
         */
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	/* 
	Création d'une méthode qui retroune la couleur qui sera ensuite override
	*/
	public String getCellColor() {
		throw new IllegalCallerException("This method should not be using. Consider it abstract.");
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", state=" + state + "]";
	}
}
