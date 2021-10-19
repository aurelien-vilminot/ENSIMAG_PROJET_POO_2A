import gui.GUISimulator;
import java.awt.Color;

public class TestImmigration {

    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        CellImmigration.setN(3);
        CellImmigration[] cellImmigrations = {
                new CellImmigration(3, 3, 1),
                new CellImmigration(5, 3, 1),
                new CellImmigration(4, 4, 1),
                new CellImmigration(5, 4, 1),
                new CellImmigration(4, 5, 1),

        };
        gui.setSimulable(new GridSimulator(gui, "Immigration", 50, cellImmigrations));
    }
}
