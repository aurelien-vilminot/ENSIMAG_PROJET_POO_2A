public class Boids {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private float detectionRadius;
    // maxforce and maxspeed ?

    public Boids(float x, float y, float detectionRadius) {
        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);

        if (detectionRadius <= 0) {
            throw new IllegalArgumentException("Radius has to be positive");
        }
        this.detectionRadius = detectionRadius;
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

    @Override
    public String toString() {
        return "Boids{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", radius=" + detectionRadius +
                '}';
    }
}
