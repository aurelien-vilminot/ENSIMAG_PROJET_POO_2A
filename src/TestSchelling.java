import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to test the game of Schelling composed of GridSimulator who implements
 * the interface Simulable using GUI, Grid, CellISchelling and Cell.
 */
public class TestSchelling {
    private static CellSchelling[] generateSchellingCells(int nbStates, int nbCells, int width, int height, int cellSize) {
        Random random = new Random();
        int maxX = (height / cellSize) - 1;
        int maxY = (width / cellSize) - 1;
        ArrayList<CellSchelling> listOfCells = new ArrayList<>();
        for (int i = 0; i < nbCells; i++) {
            int x = random.nextInt(maxX);
            int y = random.nextInt(maxY);
            int state = random.nextInt(nbStates - 1) + 1;
            listOfCells.add(new CellSchelling(x, y, state));
        }
        CellSchelling[] arr = new CellSchelling[listOfCells.size()];
        return listOfCells.toArray(arr);
    }

    public static void main(String[] args) {
        int width = 500;
        int height = 500;
        int cellSize = 20;
        int nbStates = 4;
        int nbCells = 100;
        int k = 1;
        GUISimulator gui = new GUISimulator(width, height, Color.BLACK);
        CellSchelling.setK(k);
        CellSchelling.setColors(nbStates);
        CellSchelling[] cellSchellings = generateSchellingCells(nbStates, nbCells, width, height, cellSize);
        gui.setSimulable(new GridSimulator(gui, "Schelling", cellSize, cellSchellings));
    }
}
