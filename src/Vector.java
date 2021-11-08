import java.lang.Math;

/**
 * Class of vector, used to represent the 2 coordinates of a boid at each time.
 */
public class Vector {
    private float x;
    private float y;

    /**
     * First constructor of vector, which fill the elements with an 0.
     */
    public Vector() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Overloading of the constructor, which fill the elements with the parameters.
     * @param x : value on the axis X.
     * @param y : value on the axis Y.
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Overloading of the constructor, which fill the elements with the vector in parameters.
     * @param vector : the vector to copy
     */
    public Vector(Vector vector) {
        this.x = vector.getX();
        this.y = vector.getY();
    }

    /**
     * Sums the two vectors passed into parameters.
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Sum of the 2 vectors in a new Vector
     */
    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * Subtracts the two vectors passed into parameters.
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Subtraction of the 2 vectors in a new Vector
     */
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Sum the vector to itself and modify directly its value.
     * @param v : Vector
     */
    public void add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    /**
     * Subtract the vector to itself and modify directly its value.
     * @param v : Vector
     */
    public void sub(Vector v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    /**
     * Multiply all the values of the vector by m.
     * @param m : float
     */
    public void mult(float m) {
        this.x *= m;
        this.y *= m;
    }

    /**
     * Calculate the distance between 2 vectors.
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Distance between points represented by *v1* and *v2*
     */
    public static float distance(Vector v1, Vector v2) {
        return (float) Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2));
    }

    /**
     * Calculate the norm of the vector.
     * @return Norm of the calling Vector
     */
    public float norm() {
        return (float) Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }
}
