import java.lang.Math;

public class Vector {

    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Sum of the 2 vectors in a new Vector
     */
    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Substraction of the 2 vectors in a new Vector
     */
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     * Sum the vector to itself and modify directly its value
     *
     * @param v : Vector
     */
    public void add(Vector v) {
        this.setX(this.getX() + v.getX());
        this.setY(this.getY() + v.getY());
    }

    /**
     * Substract the vector to itself and modify directly its value
     *
     * @param v : Vector
     */
    public void sub(Vector v) {
        this.setX(this.getX() - v.getX());
        this.setY(this.getY() - v.getY());
    }


    /**
     * Modify and multiply all the values of the vector by m
     *
     * @param m : float
     */
    public void mult(float m) {
        this.x *= m;
        this.y *= m;
    }


    /**
     * @param v1 : Vector
     * @param v2 : Vector
     * @return Distance between points represented by *v1* and *v2*
     */
    public static float distance(Vector v1, Vector v2) {
        return (float) Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2));
    }

    /**
     * @return Norm of the calling Vector
     */
    public float norm() {
        return (float) Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }


    @Override
    public String toString() {
        return "{x=" + x + ", y=" + y + "}";
    }
}
