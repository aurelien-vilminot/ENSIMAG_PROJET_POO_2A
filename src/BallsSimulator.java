import java.awt.Point;
import java.awt.Color;

import gui.GUISimulator;
import gui.Oval;

/**
 * Class BallsSimulator :
 *  Definition of the class who implements the interface Simulable for the game composed of balls.
 */
public class BallsSimulator extends Simulator {
    /**
     * Constructor of the interface we will simulate.
     * @param window : window of simulation.
     */
    public BallsSimulator(GUISimulator window) {
        super(window);
        this.eventManager.addEvent(new SimulatorEvent(0, this, 1, null));

        this.backend = new Balls(window.getPanelWidth(), window.getPanelHeight(),
                new Point(50,100),
                new Point(60, 10),
                new Point(10, 200),
                new Point(200,300),
                new Point(150,150),
                new Point(400,300),
                new Point(120,50),
                new Point(340,180),
                new Point(400,0)
                );
    }

    /**
     * Draw the balls with different colors.
     */
    public void draw() {
        String[] tab = {"#ff0000", "#00ff00", "#0000ff"};
        int i = 0;
        Balls balls = (Balls) this.backend;
        for (Point point : balls.getBallsList()) {
            gui.addGraphicalElement(new Oval(point.x, point.y, Color.decode("#0f2a1a"), Color.decode(tab[(i++)%3]), 50));
        }
    }
}