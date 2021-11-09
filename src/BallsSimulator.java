import java.awt.Point;
import java.awt.Color;

import gui.GUISimulator;
import gui.Oval;

/**
 * Definition of the class which manages the balls simulation by drawing them.
 * @see Simulator
 */
public class BallsSimulator extends Simulator {

    /**
     * Initialisation of the interface which will simulate by adding an event and creating balls.
     * @param window Window where the simulation takes place.
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
     * Draw the balls with three different colors.
     */
    public void draw() {
        String[] colors = {"#ff0000", "#00ff00", "#0000ff"};
        int i = 0;
        Balls balls = (Balls) this.backend;
        for (Point point : balls.getBallsList()) {
            this.gui.addGraphicalElement(new Oval(
                    point.x, point.y, Color.decode("#0f2a1a"), Color.decode(colors[(i++)%3]), 50
            ));
        }
    }
}