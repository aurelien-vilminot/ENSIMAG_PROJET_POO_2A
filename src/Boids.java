import java.awt.*;
import java.util.ArrayList;

/**
 * Creation of boids.
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
     * Constructor of boids
     * @param x : initial position of boids on the axis X.
     * @param y : initial position of boids on the axis Y.
     * @param detectionRadius : how the boid detects its environment.
     * @param xMax : maximum position of boids on the axis X.
     * @param yMax : maximum position of boids on the axis Y.
     */
    public Boids(float x, float y, float detectionRadius, float xMax, float yMax, String type) {
        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);

        if (detectionRadius <= 0) {
            throw new IllegalArgumentException("Radius has to be positive");
        }
        this.detectionRadius = detectionRadius;
        this.xMax = xMax;
        this.yMax = yMax;
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

    public void setVelocity(Vector velocity) {
        this.velocity.setX(velocity.getX());
        this.velocity.setY(velocity.getY());
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

    public boolean canApproach(Boids b) {
        if (this.type.equals("kind")) {
            return (!b.type.equals("evil"));
        }
        return true;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract Color getColor();

    public abstract int getTimeStep();

    /**
     * Rule of wind: apply wind force to the group
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the wind force
     */
    public Vector windRule(ArrayList<Boids> boidsArrayList) {
        return new Vector(0.1f, 0.9f);
    }

    /**
     * Rule of borders: keeping the flock within the area use by the gui
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the replace force applied to bj
     */
    public Vector borderRule(ArrayList<Boids> boidsArrayList) {
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

    protected void applyInitRules(ArrayList<Boids> boidsArrayList) {
        Vector vectorWindRule = this.windRule(boidsArrayList);
        Vector vectorBorderRule = this.borderRule(boidsArrayList);
        // Calculate new acceleration
        this.setAcceleration(vectorWindRule);
        this.getAcceleration().add(vectorBorderRule);
    }

    /**
     * Apply this boid's rules to modify its acceleration
     *
     * @param boidsArrayList : list of boids of the space
     */
    public abstract void applyRules(ArrayList<Boids> boidsArrayList);

    public final void updateVelocity() {
        this.velocity.add(this.acceleration);
    }

    public final void updatePosition() {
        this.position.add(this.velocity);
    }

    public void addEvent(EventManager eventManager) {
        eventManager.addEvent(new BoidsEvent(eventManager.getCurrentDate(), this));
    }

    @Override
    public final String toString() {
        return "Boids{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", radius=" + detectionRadius +
                '}';
    }
}
