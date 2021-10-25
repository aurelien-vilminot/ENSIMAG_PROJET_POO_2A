import java.util.ArrayList;
import java.lang.Math;

public class Grid {
	private Cell[][] cellArray;
	private Cell[][] initArray;
	private int height;
	private int width;
	private int stepNumber = 0;

	/* 
	Création d'une grille contenant les cellules spécifique au jeu souhaité
	int height = hauteur  de la grille
	int width = largeur de la grille
	String gameName = nom du jeu, utiliser les créer le bon type de cellules, règles du jeu ...
	Cell... cells = cellules constituants la grille
	*/
	public Grid(int height, int width, String gameName, Cell... cells) {
		this.cellArray = new Cell[height][width];
		this.initArray = new Cell[height][width];
		this.height = height;
		this.width = width;

		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				switch (gameName) {
					case "Conway":
						this.cellArray[i][j] = new CellConway(i, j, 0);
						this.initArray[i][j] = new CellConway(i, j, 0);
						break;
					case "Immigration":
						this.cellArray[i][j] = new CellImmigration(i, j, 0);
						this.initArray[i][j] = new CellImmigration(i, j, 0);
						break;
					case "Schelling":
						this.cellArray[i][j] = new CellSchelling(i, j, 0);
						this.initArray[i][j] = new CellSchelling(i, j, 0);
						break;
					default:
						// TODO: Exception
						System.out.println("!!! THE GAME DOESN'T EXIST !!!");
				}
			}
		}
		for (Cell c : cells) {
			if (c.x > this.width - 1 || c.y > this.height - 1) {
				throw new IllegalArgumentException("Init cells must be placed into the grid");
			}
			this.cellArray[c.x][c.y].state = c.state;
			this.initArray[c.x][c.y].state = c.state;
		}
	}

	/* récupération des attributs privés */
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

	/* Réinitialisation de la grille contenant les cellules du jeu souhaité */
	public void reInit(String gameName) {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				Cell c0 = this.initArray[i][j];
				switch (gameName) {
					case "Conway":
						this.cellArray[i][j] = new CellConway(c0.x, c0.y, c0.state);
						break;
					case "Immigration":
						this.cellArray[i][j] = new CellImmigration(c0.x, c0.y, c0.state);
						break;
					case "Schelling":
						this.cellArray[i][j] = new CellSchelling(c0.x, c0.y, c0.state);
						break;
					default:
						// TODO: Exception
						System.out.println("!!! THE GAME DOESN'T EXIST !!!");
				}
			}
		}
	}

	/* 
	Renvoie la liste des voisins d'une cellule donnée, en faisant attention si la ceullule se trouve sur les bords ou non.
	int x = position de la cellule sur la grille
	int y = position de la cellule sur la grille
	*/
	public ArrayList<Cell> getNeighbours(int x, int y) {
		ArrayList<Cell> nArray = new ArrayList<Cell>();
		int w = this.width - 1;
		int h = this.height - 1;
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][Math.floorMod(y-1, w)]); // topLeft
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][y]); // top
		nArray.add(this.cellArray[Math.floorMod(x-1, h)][Math.floorMod(y+1, w)]); // topRight
		nArray.add(this.cellArray[x][Math.floorMod(y+1, w)]); // right
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][Math.floorMod(y+1, w)]); // bottomRight
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][y]); // bottom
		nArray.add(this.cellArray[Math.floorMod(x+1, h)][Math.floorMod(y-1, w)]); // bottomLeft
		nArray.add(this.cellArray[x][Math.floorMod(y-1, w)]); // left
		return nArray;
	}

	/* 
	Fait avancer d'un pas le jeu actuel, en mettant à jour les états des différentes cellules
	*/
	public void step() {
		int[][] stateArray = new int[this.height][this.width];

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				// coordStates[0] = x coord of the first cell
				// coordStates[1] = y coord of the first cell
				// coordStates[2] = state of the first cell
				// coordStates[3] = x coord of the second cell
				// ...
				ArrayList<Integer> coordStates = this.cellArray[i][j].nextState(getNeighbours(i, j));
				for (int k = 0 ; k < coordStates.size() ; k++) {
					stateArray[coordStates.get(k)][coordStates.get(++k)] = coordStates.get(++k);
				}
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
		return this.cellArray[x][y].getCellColor();
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
			if (i != this.height - 1) {
				res += "\n";
			}
		}
		return res;
	}

}
