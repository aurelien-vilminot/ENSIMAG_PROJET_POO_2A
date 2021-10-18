import gui.GUISimulator;
import java.awt.*;

public class TestGameOfLife {

	public static void main(String[] args) {
		GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
		CellConway[] tab = { new CellConway(5, 5, 1), new CellConway(4, 5, 1), new CellConway(4, 6, 1),
				new CellConway(4, 4, 1) };
		gui.setSimulable(new GridSimulator(gui, "Conway", 50, tab));
	}
}
