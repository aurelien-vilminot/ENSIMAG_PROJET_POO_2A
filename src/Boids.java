import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class which represents the skeleton for boids.
 */
public abstract class Boids {
    protected Vector position;
    protected Vector velocity;
    protected Vector acceleration;
    protected float detectionRadius;
    protected float xMax;
    protected float yMax;
    protected String type;

    /**
     * Constructor of boids.
     * @param x Initial position of boids on the x-axis.
     * @param y Initial position of boids on the y-axis.
     * @param detectionRadius Radius where the boids repel each other.
     * @param xMax Maximum position of boids on the x-axis.
     * @param yMax Maximum position of boids on the y-axis.
     */
    public Boids(float x, float y, float detectionRadius, float xMax, float yMax, String type) {
        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);
        this.xMax = xMax;
        this.yMax = yMax;

        if (detectionRadius <= 0) {
            throw new IllegalArgumentException("Radius has to be positive");
        }
        this.detectionRadius = detectionRadius;

        if (!type.equals("evil") && !type.equals("kind")) {
            throw new IllegalArgumentException("Type has to be 'kind' or 'evil'");
        }
        this.type = type;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public Vector getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration.setX(acceleration.getX());
        this.acceleration.setY(acceleration.getY());
    }

    public float getDetectionRadius() {
        return this.detectionRadius;
    }

    public void setDetectionRadius(float detectionRadius) {
        this.detectionRadius = detectionRadius;
    }

    public String getType() {
        return this.type;
    }

    /**
     * Determine if boids can approach another depending on its family.
     * @param b The target boids.
     * @return True if the boids can approach boids b, else False.
     */
    public boolean canApproach(Boids b) {
        if (this.type.equals("kind")) {
            return (!b.type.equals("evil"));
        }
        return true;
    }

    public abstract Color getColor();

    public abstract int getTimeStep();

    /**
     * Rule of wind: apply wind force to the group.
     * @return Vector corresponding to the wind force
     */
    public Vector windRule() {
        return new Vector(0.1f, 0.9f);
    }

    /**
     * Rule of borders: keeping the flock within the area used by the gui.
     * @return Vector corresponding to the force applied on the boids.
     */
    public Vector borderRule() {
        Vector f = new Vector();
        Vector boidPosition = this.position;

        float mx = Math.max(10, Math.abs(this.velocity.getX()));
        if (boidPosition.getX() < 0) {
            f.setX(mx);
        } else if (boidPosition.getX() > this.xMax) {
            f.setX(-mx);
        }

        float my = Math.max(10, Math.abs(this.velocity.getY()));
        if (boidPosition.getY() < 0) {
            f.setY(my);
        } else if (boidPosition.getY() > this.yMax) {
            f.setY(-my);
        }
        return f;
    }

    /**
     * Apply the rules to modify boids' acceleration.
     * @param boidsArrayList List of boids of the space.
     */
    public void applyRules(ArrayList<Boids> boidsArrayList) {
        Vector vectorWindRule = this.windRule();
        Vector vectorBorderRule = this.borderRule();
        // Calculate new acceleration
        this.setAcceleration(vectorWindRule);
        this.getAcceleration().add(vectorBorderRule);
    }

    /**
     * Update velocity by adding the acceleration.
     */
    public final void updateVelocity() {
        this.velocity.add(this.acceleration);
    }

    /**
     * Update position by adding the velocity.
     */
    public final void updatePosition() {
        this.position.add(this.velocity);
    }

    @Override
    public final String toString() {
        return "Boids{" +
                "position=" + this.position +
                ", velocity=" + this.velocity +
                ", acceleration=" + this.acceleration +
                ", radius=" + this.detectionRadius +
                ", type=" + this.type +
                '}';
    }
}
