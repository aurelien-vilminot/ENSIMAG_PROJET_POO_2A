import java.awt.*;
import java.util.ArrayList;

/**
 * Evil boids class which extends Boids.
 * @see Boids
 */
public class BoidsEvil extends Boids {
    private static final int timeStep = 1;

    /**
     * Constructor of evil boids.
     * @see Boids for params specifications.
     */
    public BoidsEvil(float x, float y, float detectionRadius, float xMax, float yMax) {
        super(x, y, detectionRadius, xMax, yMax, "evil");
    }

    /**
     * Rule of chase: move towards the nearest approachable different-typed boids.
     * @param boidsArrayList List of boids of the space.
     * @return Vector corresponding to the chase force applied to this boids.
     */
    public Vector chaseRule(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        Boids nearest = null;
        float minDistance = 0;
        for (Boids b : boidsArrayList) {
            if (b != this) {
                if (!this.type.equals(b.type) && this.canApproach(b)) {
                    float distance = Vector.distance(this.position, b.getPosition());
                    if (distance < minDistance || minDistance == 0) {
                        nearest = b;
                        minDistance = distance;
                    }
                }
            }
        }
        if (minDistance == 0) {
            return f;
        }
        f.add(nearest.getPosition());
        f.sub(this.position);
        f.mult((float) 1 / 100);
        return f;
    }

    /**
     * Apply specific evil boids rules to modify its acceleration.
     * @param boidsArrayList List of boids of the space.
     */
    @Override
    public void applyRules(ArrayList<Boids> boidsArrayList) {
        super.applyRules(boidsArrayList);
        // Apply its own rules
        Vector vectorChaseRule = chaseRule(boidsArrayList);
        // Calculate new acceleration
        this.acceleration.add(vectorChaseRule);
    }

    @Override
    public Color getColor() {
        return Color.decode("#FF0000");
    }

    @Override
    public int getTimeStep() {
        return timeStep;
    }
}
