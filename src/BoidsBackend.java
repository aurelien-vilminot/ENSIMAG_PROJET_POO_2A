import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Create the Backend where boids evolve.
 */
public class BoidsBackend implements Backend {
    private ArrayList<Boids> boidsList = new ArrayList<>();
    private ArrayList<Boids> boidsInitList = new ArrayList<>();
    // Width of area
    private int xMax, yMax;

    /**
     * Constructor of the backend.
     * @param xMax : maximum size of X.
     * @param yMax : maximum size of Y.
     * @param boids
     */
    public BoidsBackend(int xMax, int yMax, Boids... boids) {
        this.boidsList.addAll(Arrays.asList(boids));
        for (Boids b : boids) {
            this.boidsInitList.add(new Boids(b.getPosition().getX(), b.getPosition().getY(), b.getDetectionRadius(), xMax, yMax));
        }
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public ArrayList<Boids> getBoidsList() {
        return this.boidsList;
    }

    /**
     * Reinitialize the boids, by changing their position, speed and acceleration.
     */
    public void reInit() {
        for (int i = 0; i < boidsList.size(); i++) {
            Boids currentBoid = boidsList.get(i);
            Boids initBoid = boidsInitList.get(i);
            currentBoid.setPosition(initBoid.getPosition());
            currentBoid.setDetectionRadius(initBoid.getDetectionRadius());
            currentBoid.getVelocity().setX(0);
            currentBoid.getVelocity().setY(0);
            currentBoid.getAcceleration().setX(0);
            currentBoid.getAcceleration().setY(0);
        }
    }

    /**
     * Move all boids by applying forces to them
     */
    public void step(EventManager eventManager) {
        // Important: rules have to be independent of acceleration
        for (Boids b : this.boidsList) {
            b.applyRules(this.boidsList);
        }
        for (Boids b : this.boidsList) {
            b.addEvent(eventManager);
        }
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("List of boids : \n");
        for (Boids boid : this.boidsList) {
            res.append(boid);
            res.append("\n");
        }
        return res.toString();
    }
}
