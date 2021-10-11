import java.awt.Point;
import java.awt.Color;
import java.util.ArrayList;

import gui.Simulable;
import gui.GUISimulator;
import gui.Oval;

public class BallsSimulator implements Simulable {
    private Balls balls;
    private GUISimulator gui;
    private int width;
    private int height;

    // Constructeur
    public BallsSimulator(GUISimulator window) {
        this.gui = window;
        this.width = window.getPanelWidth();
        this.height = window.getPanelHeight();
        // On initialise les boules ici
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
    }

    public ArrayList<Point> getBallsList() {
        return this.balls.getBallsList();
    }

    public void draw() {
        String[] tab = {"#ff0000", "#00ff00", "#0000ff"};
        int i = 0;
        for (Point point : this.getBallsList()) {
            gui.addGraphicalElement(new Oval(point.x, point.y, Color.decode("#0f2a1a"), Color.decode(tab[(i++)%3]), 50));
        }
    }

    @Override
    public void next() {
        System.out.println(this.balls);
        gui.reset();
        this.balls.step(5, this.width, this.height);
        this.draw();
    }

    @Override
    public void restart() {
        this.balls.reInit();
        gui.reset();
        this.draw();
        System.out.println(this.balls);
    }

}