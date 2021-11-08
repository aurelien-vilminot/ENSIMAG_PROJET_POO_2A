import java.awt.*;
import java.util.ArrayList;

public class BoidsEvil extends Boids {
    private final int step = 2;

    /**
     * Constructor of boids
     *
     * @param x               : initial position of boids on the axis X.
     * @param y               : initial position of boids on the axis Y.
     * @param detectionRadius : how the boid detects its environment.
     * @param xMax            : maximum position of boids on the axis X.
     * @param yMax            : maximum position of boids on the axis Y.
     */
    public BoidsEvil(float x, float y, float detectionRadius, float xMax, float yMax) {
        super(x, y, detectionRadius, xMax, yMax, "evil");
    }

    /**
     * Apply this boid's rules to modify its acceleration
     *
     * @param boidsArrayList : list of boids of the space
     */
    @Override
    public void applyRules(ArrayList<Boids> boidsArrayList) {
        this.applyInitRules(boidsArrayList);
    }

    @Override
    public Color getColor() {
        return Color.decode("#FF0000");
    }

    @Override
    public int getStep() {
        return this.step;
    }

//    @Override
//    public void addEvent(EventManager eventManager) {
//        eventManager.addEvent(new BoidsEvent(eventManager.getCurrentDate() + 2, this));
//    }
}
