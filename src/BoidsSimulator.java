import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulator implements Simulable {
    private static final String BORDERCOLOR = "#FFFFFF";
    private static final String BOIDCOLOR = "#0000FF";
    private static final String BACKGROUNDCOLOR = "#FF0000";
    private static final int BOIDSIZE = 5;

    private GUISimulator gui;
    private BoidsBackend boidsBackend;

    private Rectangle background;

    public BoidsSimulator(GUISimulator gui, Boids ...boids) {
        this.gui = gui;
        this.boidsBackend = new BoidsBackend(boids);
        this.background = new Rectangle(0, 0, Color.decode(BACKGROUNDCOLOR),
                Color.decode(BACKGROUNDCOLOR), gui.getPanelWidth());
    }

    public void draw() {
        gui.addGraphicalElement(this.background);

        ArrayList<Boids> boidsList = this.boidsBackend.getBoidsList();
        for (Boids boid : boidsList) {
            Vector position = boid.getPosition();
            gui.addGraphicalElement(new Rectangle((int) position.getX(), (int) position.getY(),
                    Color.decode(BORDERCOLOR), Color.decode(BOIDCOLOR), BOIDSIZE));
        }
    }

    @Override
    public void next() {
        gui.reset();
        this.boidsBackend.step();
        this.draw();
    }

    @Override
    public void restart() {
        this.boidsBackend.reInit();
        gui.reset();
        this.draw();
    }
}
