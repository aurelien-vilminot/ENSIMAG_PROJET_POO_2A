import gui.GUISimulator;
import java.awt.*;

/**
 * Class to test the game of Life of Conway composed of GridSimulator which implements
 * the interface Simulable using GUI, Grid, CellConway and Cell.
 */
public class TestGameOfLife {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(1000, 500, Color.BLACK);
		CellConway[] cellsConways = {
				new CellConway(10, 5, 1),
				new CellConway(11, 5, 1),
				new CellConway(11, 3, 1),
				new CellConway(13, 4, 1),
				new CellConway(14, 5, 1),
				new CellConway(15, 5, 1),
				new CellConway(16, 5, 1),
				new CellConway(10, 25, 1),
				new CellConway(11, 25, 1),
				new CellConway(11, 23, 1),
				new CellConway(13, 24, 1),
				new CellConway(14, 25, 1),
				new CellConway(15, 25, 1),
				new CellConway(16, 25, 1),

		};
		gui.setSimulable(new GridSimulator(gui, "Conway", 17, cellsConways));
	}
}
