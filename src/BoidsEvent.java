public class BoidsEvent extends Event {
    Boids boid;

    public BoidsEvent(long date, Boids boid) {
        super(date);
        this.boid = boid;
    }

    public void execute() {
        // Calculate new velocity
        this.boid.getVelocity().add(this.boid.getAcceleration());
        // Calculate new position
        this.boid.getPosition().add(this.boid.getVelocity());
    }
}