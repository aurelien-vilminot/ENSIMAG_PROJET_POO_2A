import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

public class BoidsSimulator implements Simulable {
    private static final Color borderColor = Color.decode("#FFFFFF");
    private static final Color boidColor = Color.decode("#0000FF");
    private static final int boidSize = 20;
    private static final float borderWidth = 3;

    private GUISimulator gui;
    private BoidsBackend boidsBackend;

    public BoidsSimulator(GUISimulator gui, Boids ...boids) {
        this.gui = gui;
        this.boidsBackend = new BoidsBackend(
                this.gui.getPanelWidth() - boidSize,
                this.gui.getPanelHeight() - boidSize,
                boids
        );
    }

    public void draw() {
        ArrayList<Boids> boidsList = this.boidsBackend.getBoidsList();
        for (Boids boid : boidsList) {
            gui.addGraphicalElement(new Triangle(boidSize, boid.getPosition(), boid.getVelocity(),
                    borderColor, boidColor, borderWidth));
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
