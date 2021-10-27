import java.lang.Math;

public class Vector {

    private float x;
    private float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * 
     * @param v1 : Vector1
     * @param v2 : Vector 2
     * @return the sum of the 2 vectors in a new Vector
     */
    public static Vector add(Vector v1, Vector v2) {
        return new Vector(v1.getX() + v2.getX(), v1.getY() + v2.getY());
    }

    /**
     *
     * @param v1 : Vector1
     * @param v2 : Vector 2
     * @return the substraction of the 2 vectors in a new Vector
     */
    public static Vector sub(Vector v1, Vector v2) {
        return new Vector(v1.getX() - v2.getX(), v1.getY() - v2.getY());
    }

    /**
     *
     * @param v :Sum the vector to itself and modify directly its value
     */
    public void add(Vector v) {
        this.setX(this.getX() + v.getX());
        this.setY(this.getY() + v.getY());
    }

    /**
     *
     * @param v :Substract the vector to itself and modify directly its value
     */
    public void sub(Vector v) {
        this.setX(this.getX() - v.getX());
        this.setY(this.getY() - v.getY());
    }



    /**
     *
     * @param m : multiply and modify all the values of the vector on which we apply the methods by b by m
     */
    public void mult(float m) {
        this.x *= m;
        this.y *= m;
    }

    public static float distance(Vector v1, Vector v2) {
        return (float) Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2));
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
}
