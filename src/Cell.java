import java.util.ArrayList;

public class Cell {
	public int x;
	public int y;
	public int state = 0;

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

	/*
	Donne le prochain état de la cellule sur laquelle on appelle la méthode, méthode créee pour être override par les classes héritées
	ArrayList<Cell> neighbours = la liste de cellule des voisins 
	 */
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		System.out.println("!!! nextState: YOU SHOULD NOT BE USING ME !!!");
		return null;
	}

	/* 
	Création d'une méthode qui retroune la couleur qui sera ensuite override
	*/
	public String getCellColor() {
		System.out.println("!!! getCellColor: YOU SHOULD NOT BE USING ME !!!");
		return "";
	}

	@Override
	public String toString() {
		return "Cell [x=" + x + ", y=" + y + ", state=" + state + "]";
	}
}
