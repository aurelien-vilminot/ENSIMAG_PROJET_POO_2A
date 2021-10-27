import java.util.ArrayList;
import java.util.Arrays;

public class Boids {
    private Vector position;
    private Vector velocity;
    private Vector acceleration;
    private float radius;
    private float orientation;
    // maxforce and maxspeed ?

    public Boids(float x, float y, float radius) {
        this.position = new Vector(x, y);
        this.velocity = new Vector(0, 0);
        this.acceleration = new Vector(0, 0);

        if (radius <= 0) {
            throw new IllegalArgumentException("Radius has to be positive");
        }
        this.radius = radius;
    }

    /**
     * Rule of cohesion
     * @param bj : a boid on which why apply the rule
     * @param boidsArrayList : list of other boids of the space
     * @return a Vector corresponding to the value of the forces applied on the boid b
     */
    public static Vector rule1(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (bj != b) {
                f.add(b.getPosition());
            }
        }
        // f is the center of mass, not including itself
        float n = boidsArrayList.size();
        f.mult(1 / (n-1));
        // Move 1% towards the center
        f.sub(bj.getPosition());
        f.mult((float) 1/100);
        return f;
    }

    /**
     * Rule of separation
     * @param bj : a boid on which why apply the rule
     * @param boidsArrayList : list of other boids of the space
     * @return a Vector corresponding to the value of the forces applied on the boid b
     */
    public static Vector rule2(Boids bj, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids b : boidsArrayList) {
            if (bj != b) {
                float distance = Vector.distance(bj.getPosition(), b.getPosition());
                if (distance < bj.radius) {
                    f.sub(Vector.sub(b.getPosition(), bj.getPosition()));
                }
            }
        }
        return f;
    }

    /**
     * Rule of alignment
     * @param b : a boid on which why apply the rule
     * @param boidsArrayList : list of other boids of the space
     * @return a Vector corresponding to the value of the forces applied on the boid b
     */
    public static Vector rule3(Boids b, ArrayList<Boids> boidsArrayList) {
        Vector f = new Vector(0, 0);
        for (Boids bi : boidsArrayList) {

        }
        // TODO: finish rule 3
        return f;
    }

    public Vector getPosition() {
        return this.position;
    }

    public void setPosition(Vector position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Vector velocity) {
        this.velocity = velocity;
    }

    public Vector getAcceleration() {
        return this.acceleration;
    }

    public void setAcceleration(Vector acceleration) {
        this.acceleration = acceleration;
    }

    public float getRadius() {
        return this.radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float getOrientation() {
        return this.orientation;
    }

    public void setOrientation(float orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "Boids{" +
                "position=" + position +
                ", velocity=" + velocity +
                ", acceleration=" + acceleration +
                ", radius=" + radius +
                ", orientation=" + orientation +
                '}';
    }
}
