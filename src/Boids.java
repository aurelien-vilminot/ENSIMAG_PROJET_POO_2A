import java.util.ArrayList;

/**
 * Creation of boids.
 */
public class Boids {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private float detectionRadius;
    private float xMax;
    private float yMax;

    /**
     * Constructor of boids
     * @param x : initial position of boids on the axis X.
     * @param y : initial position of boids on the axis Y.
     * @param detectionRadius : how the boid detects its environment.
     * @param xMax : maximum position of boids on the axis X.
     * @param yMax : maximum position of boids on the axis Y.
     */
    public Boids(float x, float y, float detectionRadius, float xMax, float yMax) {
        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);

        if (detectionRadius <= 0) {
            throw new IllegalArgumentException("Radius has to be positive");
        }
        this.detectionRadius = detectionRadius;
        this.xMax = xMax;
        this.yMax = yMax;
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

    /**
     * Rule of cohesion: move towards the perceived center
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the cohesion force applied to this boid
     */
    public Vector rule1(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                f.add(b.getPosition());
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
    public Vector rule2(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                float distance = Vector.distance(this.position, b.getPosition());
                if (distance < this.detectionRadius) {
                    f.sub(Vector.sub(b.getPosition(), this.position));
                }
            }
        }
        f.mult(1 / (float) 20);
        return f;
    }

    /**
     * Rule of alignment: matching the "perceived velocity" of the group
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the alignment force applied to this boid
     */
    public Vector rule3(ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != this) {
                f.add(b.getVelocity());
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
     * Rule of wind: apply wind force to the group
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the wind force
     */
    public Vector rule4(ArrayList<Boids> boidsArrayList) {
        return new Vector(0.1f, 0.9f);
    }

    /**
     * Rule of borders: keeping the flock within the area use by the gui
     *
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the replace force applied to bj
     */
    public Vector rule5(ArrayList<Boids> boidsArrayList) {
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
     * Apply this boid's rules to modify its acceleration
     *
     * @param boidsArrayList : list of boids of the space
     */
    public void applyRules(ArrayList<Boids> boidsArrayList) {
        Vector f1 = this.rule1(boidsArrayList);
        Vector f2 = this.rule2(boidsArrayList);
        Vector f3 = this.rule3(boidsArrayList);
        Vector f4 = this.rule4(boidsArrayList);
        Vector f5 = this.rule5(boidsArrayList);
        // Calculate new acceleration
        this.setAcceleration(f1);
        this.getAcceleration().add(f2);
        this.getAcceleration().add(f3);
        this.getAcceleration().add(f4);
        this.getAcceleration().add(f5);
    }

    public void updateVelocity() {
        this.velocity.add(this.acceleration);
    }

    public void updatePosition() {
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
