import gui.GUISimulator;

import java.awt.*;

public class TestBoids {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        Boids[] boids = {
                new Boids(1, 1, 5),
                new Boids(5, 2, 10),
                new Boids(4, 1, 2),
                new Boids(7, 3, 6),
        };
        gui.setSimulable(new BoidsSimulator(gui, boids));
    }
}
