import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * Create the Backend where boids evolve.
 */
public class BoidsBackend {
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
            this.boidsInitList.add(new Boids(b.getPosition().getX(), b.getPosition().getY(), b.getDetectionRadius()));
        }
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public ArrayList<Boids> getBoidsList() {
        return this.boidsList;
    }

    /**
     * Re initialize the boids, by changing their position, speed ans acceleration.
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
     * Rule of cohesion: move towards the perceived center
     *
     * @param bj             : a boid on which why apply the rule
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the cohesion force applied to bj
     */
    public Vector rule1(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != bj) {
                f.add(b.getPosition());
            }
        }
        // f is the center of mass, not including itself
        float n = boidsArrayList.size();
        f.mult(1 / (n - 1));
        // Move 1% towards the center
        f.sub(bj.getPosition());
        f.mult((float) 1 / 100);
        return f;
    }

    /**
     * Rule of separation: move away from other near boids
     *
     * @param bj             : a boid on which why apply the rule
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the separation force applied to bj
     */
    public Vector rule2(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != bj) {
                float distance = Vector.distance(bj.getPosition(), b.getPosition());
                if (distance < bj.getDetectionRadius()) {
                    f.sub(Vector.sub(b.getPosition(), bj.getPosition()));
                }
            }
        }
        f.mult(1 / (float) 20);
        return f;
    }

    /**
     * Rule of alignment: matching the "perceived velocity" of the group
     *
     * @param bj             : a boid on which why apply the rule
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the alignment force applied to bj
     */
    public Vector rule3(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != bj) {
                f.add(b.getVelocity());
            }
        }
        // f is the perceived velocity
        float n = boidsArrayList.size();
        f.mult(1 / (n - 1));
        // Add 1/8th to the boid's current velocity
        f.sub(bj.getVelocity());
        f.mult(1 / (float) 8);
        return f;
    }

    /**
     * Rule of wind: apply wind force to the group
     * @return Vector corresponding to the wind force
     */
    public Vector rule4() {
        return new Vector(0.1f, 0.9f);
    }

    /**
     * Rule of borders: keeping the flock within the area use by the gui
     * @param boid: boid on which why apply the rule
     * @return Vector corresponding to the replace force applied to bj
     */
    public Vector rule5(Boids boid) {
        Vector f = new Vector();
        Vector boidPosition = boid.getPosition();

        if (boidPosition.getX() < 0) {
            f.setX(10);
        } else if (boidPosition.getX() > this.xMax) {
            f.setX(-10);
        }

        if (boidPosition.getY() < 0) {
            f.setY(10);
        } else if (boidPosition.getY() > this.yMax) {
            f.setY(-10);
        }
        return f;
    }

    /**
     * Move all boids by applying forces to them
     */
    public void step() {
        // Important: rules have to be independant of acceleration
        for (Boids b : this.boidsList) {
            Vector f1 = rule1(b, this.boidsList);
            Vector f2 = rule2(b, this.boidsList);
            Vector f3 = rule3(b, this.boidsList);
            Vector f4 = rule4();
            Vector f5 = rule5(b);
            // Calculate new acceleration
            b.setAcceleration(f1);
            b.getAcceleration().add(f2);
            b.getAcceleration().add(f3);
            b.getAcceleration().add(f4);
            b.getAcceleration().add(f5);
        }

        // Apply changes at the same time
        for (Boids b : this.boidsList) {
            // Calculate new velocity
            b.getVelocity().add(b.getAcceleration());
            // Calculate new position
            b.getPosition().add(b.getVelocity());
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
