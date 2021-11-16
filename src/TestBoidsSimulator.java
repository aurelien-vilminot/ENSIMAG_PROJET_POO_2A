import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class to test the class Boids.
 */
public class TestBoidsSimulator {

    private static ArrayList<int[]> generateCoordinates(int guiSize, int nbCoordinates) {
        Random random = new Random();
        int minRange = 1;
        int maxRange = guiSize - 1;
        ArrayList<int[]> listOfCoordinates = new ArrayList<>();
        for (int i = 0 ; i < nbCoordinates ; ++i) {
            // Generate random coords, the range is inclusive
            int x = random.nextInt((maxRange - minRange) + 1) + minRange;
            int y = random.nextInt((maxRange - minRange) + 1) + minRange;
            listOfCoordinates.add(new int[]{x,y});
        }
        return listOfCoordinates;
    }

    public static void main(String[] args) {
        int guiSize = 500;
        GUISimulator gui = new GUISimulator(guiSize, guiSize, Color.decode("#B1EEFE"));

        int nbBoids = 100;
        Boids[] boids = new Boids[nbBoids];
        ArrayList<int[]> listOfCoordinates = generateCoordinates(guiSize, nbBoids);
        for (int i = 0 ; i  < nbBoids ; i += 1) {
            if (i%10 != 0) {
                boids[i] = new BoidsKind(listOfCoordinates.get(i)[0], listOfCoordinates.get(i)[1], 50, guiSize, guiSize);
            } else {
                boids[i] = new BoidsEvil(listOfCoordinates.get(i)[0], listOfCoordinates.get(i)[1], 50, guiSize, guiSize);
            }
        }

        gui.setSimulable(new BoidsSimulator(gui, boids));
    }
}
