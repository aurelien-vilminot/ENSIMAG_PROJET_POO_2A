import gui.GUISimulator;

import java.awt.*;

/**
 * Class to test the game of Schelling composed of GridSimulator who implements
 * the interface Simulable using GUI, Grid, CellISchelling and Cell.
 */
public class TestSchelling {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        CellSchelling.setK(1);
        CellSchelling.setColors(4);
        CellSchelling[] cellSchellings = {
                new CellSchelling(1, 1, 3),
                new CellSchelling(1, 5, 1),
                new CellSchelling(4, 3, 1),
                new CellSchelling(3, 2, 1),
                new CellSchelling(3, 3, 1),
                new CellSchelling(4, 2, 2),
                new CellSchelling(9, 5, 2),
                new CellSchelling(1, 9, 2),
                new CellSchelling(7, 7, 2),
                new CellSchelling(8, 7, 1),
                new CellSchelling(8, 8, 1),
                new CellSchelling(7, 8, 1),
        };
        gui.setSimulable(new GridSimulator(gui, "Schelling", 50, cellSchellings));
    }
}
