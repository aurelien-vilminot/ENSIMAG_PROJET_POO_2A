import gui.GUISimulator;

import java.awt.*;

public class TestSchelling {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        CellSchelling.setK(2);
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
        };
        gui.setSimulable(new GridSimulator(gui, "Schelling", 50, cellSchellings));
    }
}
