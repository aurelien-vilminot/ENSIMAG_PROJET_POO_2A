import gui.GUISimulator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to test the game of 'Immigration' composed of GridSimulator who implements
 * the interface Simulable using GUI, Grid, CellImmigration and Cell.
 */
public class TestImmigration {
    private static CellImmigration[] generateImmigrationCells(int nbStates, int nbCells, int guiSize, int cellSize) {
        Random random = new Random();
        int maxRange = (guiSize / cellSize) - 1;
        ArrayList<CellImmigration> listOfCells = new ArrayList<>();
        for (int i = 0; i < nbCells; i++) {
            int x = random.nextInt(maxRange);
            int y = random.nextInt(maxRange);
            int state = random.nextInt(nbStates - 1) + 1;
            listOfCells.add(new CellImmigration(x, y, state));
        }
        CellImmigration[] arr = new CellImmigration[listOfCells.size()];
        return listOfCells.toArray(arr);
    }

    public static void main(String[] args) {
        int guiSize = 500;
        int cellSize = 20;
        int nbStates = 4;
        int nbCells = 100;
        GUISimulator gui = new GUISimulator(guiSize, guiSize, Color.BLACK);
        CellImmigration.setN(nbStates);
        CellImmigration[] cellImmigrations = generateImmigrationCells(nbStates, nbCells, guiSize, cellSize);
        gui.setSimulable(new GridSimulator(gui, "Immigration", cellSize, cellImmigrations));
    }
}
