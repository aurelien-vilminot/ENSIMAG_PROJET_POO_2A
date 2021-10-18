import gui.GUISimulator;
import java.awt.*;

public class TestGameOfLife {

    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        CellConway cell1 = new CellConway(0, 0,1);
        CellConway cell2 = new CellConway(4,5,1);
        CellConway cell3 = new CellConway(6,8,1);
        gui.setSimulable(new GridSimulator(gui, "Conway", 5, cell1, cell2, cell3));
    }
}
