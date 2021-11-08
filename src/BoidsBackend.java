import java.util.*;

/**
 * Create the Backend where boids evolve.
 */
public class BoidsBackend implements Backend {
    private ArrayList<Boids> boidsList = new ArrayList<>();
    private ArrayList<Vector> boidsPositionsInitList = new ArrayList<>();
    private ArrayList<Float> boidsDetectionRadiusInitList = new ArrayList<>();
    private HashMap<String, Integer> typeOfBoids = new HashMap<>();
    // Width of area
    private int xMax, yMax;

    /**
     * Constructor of the backend.
     * @param xMax : maximum size of X.
     * @param yMax : maximum size of Y.
     * @param boids : list of inits boids
     */
    public BoidsBackend(int xMax, int yMax, Boids... boids) {
        this.boidsList.addAll(Arrays.asList(boids));
        for (Boids b : boids) {
            this.boidsPositionsInitList.add(new Vector(b.getPosition()));
            this.boidsDetectionRadiusInitList.add(b.getDetectionRadius());
            if (!this.typeOfBoids.containsKey(b.getType())) {
                this.typeOfBoids.put(b.getType(), b.getTimeStep());
            }
        }
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public ArrayList<Boids> getBoidsList() {
        return this.boidsList;
    }

    public HashMap<String, Integer> getTypeOfBoids() {
        return this.typeOfBoids;
    }

    /**
     * Reinitialize the boids, by changing their position, speed and acceleration.
     */
    public void reInit() {
        for (int i = 0; i < this.boidsList.size(); i++) {
            Boids currentBoid = this.boidsList.get(i);

            currentBoid.setPosition(this.boidsPositionsInitList.get(i));
            currentBoid.setDetectionRadius(this.boidsDetectionRadiusInitList.get(i));
            currentBoid.getVelocity().setX(0);
            currentBoid.getVelocity().setY(0);
            currentBoid.getAcceleration().setX(0);
            currentBoid.getAcceleration().setY(0);
        }
    }

    /**
     * Move boids by applying forces to them
     */
    public void step(String type) {
        for (Boids b : this.boidsList) {
            // Check if this type of boids needs to be updated
            if (Objects.equals(b.getType(), type)) {
                b.applyRules(this.boidsList);
                // Calculate new velocity
                b.updateVelocity();
                // Calculate new position
                b.updatePosition();
            }
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
