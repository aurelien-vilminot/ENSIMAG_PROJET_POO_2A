import java.awt.*;
import java.util.ArrayList;

public class BoidsKind extends Boids {
    private static final int timeStep = 1;

    /**
     * Constructor of boids
     *
     * @param x               : initial position of boids on the axis X.
     * @param y               : initial position of boids on the axis Y.
     * @param detectionRadius : how the boid detects its environment.
     * @param xMax            : maximum position of boids on the axis X.
     * @param yMax            : maximum position of boids on the axis Y.
     */
    public BoidsKind(float x, float y, float detectionRadius, float xMax, float yMax) {
        super(x, y, detectionRadius, xMax, yMax, "kind");
    }

    /**
     * Rule of cohesion: move towards the perceived center
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the cohesion force applied to this boid
     */
    public Vector cohesionRule(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                if (this.type.equals(b.type)) {
                    f.add(b.getPosition());
                }
            }
        }
        // f is the center of mass, not including itself
        float n = boidsArrayList.size();
        f.mult(1 / (n - 1));
        // Move 1% towards the center
        f.sub(this.position);
        f.mult((float) 1 / 100);
        return f;
    }

    /**
     * Rule of separation: move away from other near boids
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the separation force applied to this boid
     */
    public Vector separationRule(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                float distance = Vector.distance(this.position, b.getPosition());
                if (distance < this.detectionRadius) {
                    f.sub(Vector.sub(b.getPosition(), this.position));
                }
            }
        }
        f.mult(1 / (float) 50);
        return f;
    }

    /**
     * Rule of alignment: matching the "perceived velocity" of the group
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the alignment force applied to this boid
     */
    public Vector alignmentRule(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                if (this.type.equals(b.type)) {
                    f.add(b.getVelocity());
                }
            }
        }
        // f is the perceived velocity
        float n = boidsArrayList.size();
        f.mult(1 / (n - 1));
        // Add 1/8th to the boid's current velocity
        f.sub(this.velocity);
        f.mult(1 / (float) 8);
        return f;
    }

    /**
     * Rule of fear: move away from the nearest unapproachable boid
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the fear force applied to this boid
     */
    public Vector fearRule(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        Boids nearest = null;
        float minDistance = 0;
        for (Boids b : boidsArrayList) {
            if (b != this) {
                if (!this.canApproach(b)) {
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
        f.add(this.position);
        f.sub(nearest.getPosition());
        f.mult((float) 1 / 100);
        return f;
    }

    /**
     * Apply this boid's rules to modify its acceleration
     *
     * @param boidsArrayList : list of boids of the space
     */
    @Override
    public void applyRules(ArrayList<Boids> boidsArrayList) {
        super.applyRules(boidsArrayList);
        // Apply its own rules
        Vector f1 = this.cohesionRule(boidsArrayList);
        Vector f2 = this.separationRule(boidsArrayList);
        Vector f3 = this.alignmentRule(boidsArrayList);
        Vector f4 = this.fearRule(boidsArrayList);
        // Calculate new acceleration
        this.acceleration.add(f1);
        this.acceleration.add(f2);
        this.acceleration.add(f3);
        this.acceleration.add(f4);
    }

    @Override
    public Color getColor() {
        return Color.decode("#0000FF");
    }

    @Override
    public int getTimeStep() {
        return timeStep;
    }
}
