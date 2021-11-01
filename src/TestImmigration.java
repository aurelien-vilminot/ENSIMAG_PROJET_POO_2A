import gui.GUISimulator;
import java.awt.Color;

/**
 * Class to test the game of 'Immigration' composed of GridSimulator who implements
 * the interface Simulable using GUI, Grid, CellImmigration and Cell.
 */
public class TestImmigration {

    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        CellImmigration.setN(4);
        CellImmigration[] cellImmigrations = {
                new CellImmigration(1, 1, 3),
                new CellImmigration(3, 1, 1),
                new CellImmigration(4, 1, 1),
                new CellImmigration(1, 2, 3),
                new CellImmigration(2, 2, 1),
                new CellImmigration(3, 2, 1),
                new CellImmigration(4, 2, 1),
                new CellImmigration(5, 2, 2),
                new CellImmigration(1, 3, 1),
                new CellImmigration(2, 3, 1),
                new CellImmigration(3, 3, 3),
                new CellImmigration(4, 3, 2),
                new CellImmigration(5, 3, 2),
                new CellImmigration(2, 4, 1),
                new CellImmigration(3, 4, 2),
                new CellImmigration(4, 4, 2),
                new CellImmigration(5, 4, 2),
                new CellImmigration(2, 5, 3),
                new CellImmigration(3, 5, 2),
                new CellImmigration(4, 5, 2),
                new CellImmigration(5, 5, 1),

        };
        gui.setSimulable(new GridSimulator(gui, "Immigration", 50, cellImmigrations));
    }
}
