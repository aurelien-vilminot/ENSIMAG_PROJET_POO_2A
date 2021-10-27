import java.util.ArrayList;
import java.util.Arrays;

public class BoidsBackend {
    private ArrayList<Boids> boidsList = new ArrayList<>();
    private ArrayList<Boids> boidsInitList = new ArrayList<>();

    public BoidsBackend(Boids ...boids) {
        this.boidsList.addAll(Arrays.asList(boids));
        for (Boids boid : boids) {
            boidsList.add(new Boids(boid.getPosition().getX(), boid.getPosition().getY(), boid.getRadius()));
        }
    }

    public ArrayList<Boids> getBoidsList() {
        return this.boidsList;
    }

    public void reInit() {
        for (int i = 0 ; i < boidsList.size() ; ++i) {
            Boids currentBoid = boidsList.get(i);
            Boids initBoid = boidsInitList.get(i);
            currentBoid.setPosition(initBoid.getPosition());
            currentBoid.setRadius(initBoid.getRadius());
            currentBoid.setOrientation(initBoid.getOrientation());
            currentBoid.getVelocity().setX(0);
            currentBoid.getVelocity().setY(0);
            currentBoid.getAcceleration().setX(0);
            currentBoid.getAcceleration().setY(0);
        }

    }

    /**
     * Move all boids by applying forces to them
     */
    public void step() {
        // TODO : faire tous les changement en même temps
        for (Boids boid : this.boidsList) {
            Vector v1 = Boids.rule1(boid, this.boidsList);
            Vector v2 = Boids.rule2(boid, this.boidsList);
            Vector v3 = Boids.rule3(boid, this.boidsList);
            boid.getVelocity().add(v1);
            boid.getVelocity().add(v2);
            boid.getVelocity().add(v3);
            boid.getPosition().add(boid.getVelocity());
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
