import java.lang.Math;

/**
 * Class Vector, which implements a 2D math vector.
 */
public class Vector {
    private float x;
    private float y;

    /**
     * Constructor of vector.
     * @param x Value on the axis X.
     * @param y Value on the axis Y.
     */
    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor of vector, which fills the elements with an 0.
     */
    public Vector() {
        this(0, 0);
    }

    /**
     * Constructor of vector, which fills the elements with the vector in parameters.
     * @param vector The vector to copy.
     */
    public Vector(Vector vector) {
        this(vector.getX(), vector.getY());
    }

    /**
     * Subtracts the two vectors passed into parameters.
     * @param v1 Vector.
     * @param v2 Vector.
     * @return Subtraction of the 2 vectors in a new Vector.
     */
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Sum the vector to itself and modify directly its value.
     * @param v Vector.
     */
    public void add(Vector v) {
        this.x += v.getX();
        this.y += v.getY();
    }

    /**
     * Subtract the vector to itself and modify directly its value.
     * @param v Vector.
     */
    public void sub(Vector v) {
        this.x -= v.getX();
        this.y -= v.getY();
    }

    /**
     * Multiply all the values of the vector by a coefficient.
     * @param m float.
     */
    public void mult(float m) {
        this.x *= m;
        this.y *= m;
    }

    /**
     * Calculate the distance between 2 vectors.
     * @param v1 Vector.
     * @param v2 Vector.
     * @return Distance between the points represented by the vectors.
     */
    public static float distance(Vector v1, Vector v2) {
        return (float) Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2));
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
