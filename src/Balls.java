import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Class Balls :
 * Definition of the Balls which will be used by BallsSimulator .
 * Here, we decide that the balls have a speed of 10 on X and 5 on y.
 *
 *
 *
 */
public class Balls implements Backend{
    private ArrayList<Point> ballsList = new ArrayList<>();
    private ArrayList<float[]> velList = new ArrayList<>();
    private ArrayList<int[]> posInit = new ArrayList<>();

    // Width of area
    private int xMax, yMax;

    private final int velX = 10;
    private final int velY = 5;

    /**
     * 1st constructor of Balls without any parameters.
     */
    public Balls() {
        for (int i = 0; i < 10; ++i) {
            ballsList.add(new Point(i, 0));
            velList.add(new float[]{velX, velY});
            posInit.add(new int[]{i, 0});
        }
    }

    /**
     * overloading of the constructor of Balls taking coordinates of the ball we will create in parameters
     * @param tab : List of coordinates with value on X et Y.
     */
    public Balls(int xMax, int yMax, Point... tab) {
        this.xMax = xMax;
        this.yMax = yMax;
        for (Point ball : tab) {
            ballsList.add(ball);
            velList.add(new float[]{velX, velY});
            posInit.add(new int[]{ball.x, ball.y});
        }
    }

    public ArrayList<Point> getBallsList() {
        return ballsList;
    }

    /**
     * Translate a ball from the coordinates given in parameters.
     * @param dx : value of the translation on X
     * @param dy : value of the translation on Y
     */
    public void translate(int dx, int dy) {
        for (Point ball : this.ballsList) {
            ball.translate(dx, dy);
        }
    }

    /**
     * Initialized the coordinates of the List of Balls with the initial the values of
     * the list "posInit".
     */
    public void reInit() {
        for (int i = 0; i < this.ballsList.size(); i++) {
            translateInit(ballsList.get(i), posInit.get(i));
        }
    }

    /**
     * Take a step forward of the all the balls in the list of balls.
     * Corresponding to the changement applied on the balls with the button next of the simulation.
     */
    public void step(String type) {
        float dt = 5;
        for (int i = 0; i < this.ballsList.size(); i++) {
            Point pos = this.ballsList.get(i);
            float[] vel = this.velList.get(i);
            int dx = Math.round(dt * vel[0]);
            int dy = Math.round(dt * vel[1]);

            // Reverse velocity if the ball hits the wall
            if (pos.x + dx > this.xMax || pos.x + dx < 0) {
                vel[0] = -vel[0];
                dx = -dx;
            }
            if (pos.y + dy > this.yMax || pos.y + dy < 0) {
                vel[1] = -vel[1];
                dy = -dy;
            }
            pos.translate(dx, dy);
        }
    }

    /**
     * Initialise the position of the ball given in parameter on the position
     * choose also given in parameter.
     * @param ball : Ball which coordinates will change.
     * @param pos : integer coordinate on X et on Y of the new position.
     */
    private void translateInit(Point ball, int[] pos) {
        ball.move(pos[0], pos[1]);
    }

    @Override
    public String toString() {
        StringBuilder strToReturn = new StringBuilder();
        for (Point ball : this.ballsList) {
            strToReturn.append(ball.x).append(";").append(ball.y).append("\n");
        }
        return strToReturn.toString();
    }
}
