import java.util.ArrayList;
import java.util.Arrays;

public class BoidsBackend {
    private ArrayList<Boids> boidsList = new ArrayList<>();
    private ArrayList<Boids> boidsInitList = new ArrayList<>();

    public BoidsBackend(Boids... boids) {
        // this.boidsList.addAll(Arrays.asList(boids));
        for (Boids b : boids) {
            boidsList.add(new Boids(b.getPosition().getX(), b.getPosition().getY(), b.getRadius()));
            boidsInitList.add(new Boids(b.getPosition().getX(), b.getPosition().getY(), b.getRadius()));
        }
    }

    public ArrayList<Boids> getBoidsList() {
        return this.boidsList;
    }

    public void reInit() {
        for (int i = 0; i < boidsList.size(); i++) {
            Boids currentBoid = boidsList.get(i);
            Boids initBoid = boidsInitList.get(i);
            currentBoid.setPosition(initBoid.getPosition());
            currentBoid.setRadius(initBoid.getRadius());
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
    public static Vector rule1(Boids bj, ArrayList<Boids> boidsArrayList) {
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
    public static Vector rule2(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (b != bj) {
                float distance = Vector.distance(bj.getPosition(), b.getPosition());
                if (distance < bj.getRadius()) {
                    f.sub(Vector.sub(b.getPosition(), bj.getPosition()));
                }
            }
        }
        return f;
    }

    /**
     * Rule of alignment: matching the "perceived velocity" of the group
     *
     * @param bj             : a boid on which why apply the rule
     * @param boidsArrayList : list of boids of the space
     * @return Vector corresponding to the alignment force applied to bj
     */
    public static Vector rule3(Boids bj, ArrayList<Boids> boidsArrayList) {
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
     * Move all boids by applying forces to them
     */
    public void step() {
        // TODO: borders ? (rule or circular...)
        // Important: rules have to be independant of acceleration
        for (Boids b : this.boidsList) {
            Vector f1 = rule1(b, this.boidsList);
            Vector f2 = rule2(b, this.boidsList);
            Vector f3 = rule3(b, this.boidsList);
            // Calculate new acceleration
            b.getAcceleration().add(f1);
            b.getAcceleration().add(f2);
            b.getAcceleration().add(f3);
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
