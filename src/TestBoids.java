import gui.GUISimulator;

import java.awt.*;
import java.util.Random;

public class TestBoids {
    public static void main(String[] args) {
        int guiSize = 500;
        GUISimulator gui = new GUISimulator(guiSize, guiSize, Color.decode("#B1EEFE"));

        Random random = new Random();
        int minRange = 1;
        int maxRange = guiSize - 1;
        int nbBoids = 5;
        Boids[] boids = new Boids[nbBoids];
        for (int i = 0 ; i  < nbBoids ; ++i) {
            // Generate random coords, the range is inclusive
            int x = random.nextInt((maxRange - minRange) + 1) + minRange;
            int y = random.nextInt((maxRange - minRange) + 1) + minRange;
            boids[i] = new Boids(x, y, 50);
        }

        gui.setSimulable(new BoidsSimulator(gui, boids));
    }
}
