import java.util.ArrayList;


public class CellConway extends Cell {
	/* construction des cellules spécifiques à Conway */
	public CellConway(int x, int y) {
		super(x, y);
	}

	/* construction des cellules spécifiques à Conway */
	public CellConway(int x, int y, int state) {
		super(x, y, state);
	}

	/* 
	Donne le prochain état de la cellule sur laquelle on appelle la méthode
	ArrayList<Cell> neighbours = la liste de cellule des voisins 
	*/
	@Override
	public ArrayList<int[]> nextState(ArrayList<Cell> neighbours) {
		int nbLivingN = 0;
		ArrayList<int[]> coordStateArray = new ArrayList<int[]>();

		for (Cell c : neighbours) {
			if (c.state == 1) {
				nbLivingN++;
			}
		}

		if (this.state == 1 && (nbLivingN == 2 || nbLivingN == 3)) {
			// Rule 1: Any live cell with two or three live neighbours survives
			coordStateArray.add(new int[]{this.x, this.y, 1});
		} else if (this.state == 0 && nbLivingN == 3) {
			// Rule 2: Any dead cell with three live neighbours becomes a live cell
			coordStateArray.add(new int[]{this.x, this.y, 1});
		} else {
			// Rule 3: All other live cells die in the next generation. Similarly, all other
			// dead cells stay dead
			coordStateArray.add(new int[]{this.x, this.y, 0});
		}
		return coordStateArray;
	}

	/* retourne la couleur selon l'état de la cellule */
	public String getCellColor() {
		if (this.state == 0) {
			return "#F2F2F2";
		} else {
			return "#03A5FC";
		}
	}
}
