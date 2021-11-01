import gui.GUISimulator;
import gui.Rectangle;
import gui.Simulable;

import java.awt.*;
import java.util.ArrayList;

/**
 * Management of the display of BoidsBackend.
 * Implements the interface Simulable which cares of the display.
 */
public class BoidsSimulator extends Simulator {
    private static final Color borderColor = Color.decode("#FFFFFF");
    private static final Color boidColor = Color.decode("#0000FF");
    private static final int boidSize = 20;
    private static final float borderWidth = 3;

    /**
     * Constructor of an object who will display BoidsBackend, using the GUISimulator.
     * @param gui : an instance of GUISimulator
     * @param boids : boid composing the BoidsBackend
     */
    public BoidsSimulator(GUISimulator gui, Boids ...boids) {
        super(gui);
        this.backend = new BoidsBackend(
                this.gui.getPanelWidth() - boidSize,
                this.gui.getPanelHeight() - boidSize,
                boids
        );
    }

    /**
     * 	 * Draw the boids with different colors.
     */
    public void draw() {
        BoidsBackend boidsBackend = (BoidsBackend) this.backend;
        ArrayList<Boids> boidsList = boidsBackend.getBoidsList();
        for (Boids boid : boidsList) {
            gui.addGraphicalElement(new Triangle(boidSize, boid.getPosition(), boid.getVelocity(),
                    borderColor, boidColor, borderWidth));
        }
    }
}
