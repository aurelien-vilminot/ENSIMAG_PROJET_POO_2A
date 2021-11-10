import gui.GUISimulator;

import java.awt.*;
import java.util.ArrayList;

/**
 * Definition of the class which manages the boids simulation by drawing them.
 * @see Simulator
 */
public class BoidsSimulator extends Simulator {
    private final Color BORDER_COLOR = Color.decode("#FFFFFF");
    private final float BORDER_WIDTH = 1;
    private final int BOIDS_SIZE = 10;

    /**
     * Initialisation of the interface which will simulate by adding an event per type of boids.
     * @param gui Window where the simulation takes place.
     * @param boids Boids which composed the space.
     */
    public BoidsSimulator(GUISimulator gui, Boids ...boids) {
        super(gui);
        this.backend = new BoidsBackend(boids);

        // Create an event for each type of boids with its time step
        BoidsBackend boidsBackend = (BoidsBackend) this.backend;
        boidsBackend.getTypesOfBoids().forEach((key, value) -> this.eventManager.addEvent(
                new SimulatorEvent(0, this, value, key)
        ));
    }

    /**
     * Draw all boids.
     * @see Triangle
     */
    public void draw() {
        BoidsBackend boidsBackend = (BoidsBackend) this.backend;
        ArrayList<Boids> boidsList = boidsBackend.getBoidsList();
        for (Boids boid : boidsList) {
            gui.addGraphicalElement(new Triangle(this.BOIDS_SIZE, boid.getPosition(), boid.getVelocity(),
                    this.BORDER_COLOR, boid.getColor(), this.BORDER_WIDTH));
        }
    }
}
