import gui.GUISimulator;
import java.awt.Color;

/**
 * Class to test the class BallsSimulator.
 */
public class TestBallsSimulator {

    public static void main(String[] args) {
        GUISimulator gui = new GUISimulator(500, 500, Color.BLACK);
        gui.setSimulable(new BallsSimulator(gui));
    }
}
