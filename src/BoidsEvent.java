public class BoidsEvent extends Event {
    Boids boid;

    public BoidsEvent(long date, Boids boid) {
        super(date);
        this.boid = boid;
    }

    public void execute() {
        // Calculate new velocity
        this.boid.updateVelocity();
        // Calculate new position
        this.boid.updatePosition();
    }
}