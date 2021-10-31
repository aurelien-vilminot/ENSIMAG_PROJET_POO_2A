import gui.GUISimulator;

import java.awt.*;

public class TestBoids {
    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.decode("#b1eefe"));
        Boids[] boids = {
                new Boids(100, 100, 50),
                new Boids(400, 200, 50),
                new Boids(300, 300, 50),
        };
        gui.setSimulable(new BoidsSimulator(gui, boids));
    }
}
