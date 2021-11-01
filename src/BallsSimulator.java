import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;

/**
 * Class BallsSimulator :
 *  Definition of the class who implements the interface Simulable for the game composed of balls.
 */
public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;
    private int width;
    private int height;
    private EventManager eventManager = new EventManager();

    /**
     * Constructor of the interface we will simulate.
     * @param window : window of simulation.
     */
    public BallsSimulator(GUISimulator window) {
        this.gui = window;
        this.width = window.getPanelWidth();
        this.height = window.getPanelHeight();

        this.balls = new Balls(
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
        this.eventManager.addEvent(new BallsEvent(1, this));
    }

    public ArrayList<Point> getBallsList() {
        return this.balls.getBallsList();
    }

    /**
     * Draw the balls with different colors.
     */
    public void draw() {
        String[] tab = {"#ff0000", "#00ff00", "#0000ff"};
        int i = 0;
        for (Point point : this.getBallsList()) {
            gui.addGraphicalElement(new Oval(point.x, point.y, Color.decode("#0f2a1a"), Color.decode(tab[(i++)%3]), 50));
        }
    }

    public Balls getBalls() {
        return this.balls;
    }

    public GUISimulator getGui() {
        return this.gui;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public EventManager getEventManager() {
        return this.eventManager;
    }

    @Override
    public void next() {
        this.eventManager.next();
    }

    @Override
    public void restart() {
        this.balls.reInit();
        this.gui.reset();
        this.draw();
    }

}