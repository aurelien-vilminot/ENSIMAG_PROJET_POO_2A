import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Definition of the balls backend which will be used by BallsSimulator.
 * @implNote  The balls have a speed of 10 on x-axis and 5 on y-axis.
 */
public class Balls implements Backend{
    private ArrayList<Point> ballsList = new ArrayList<>();
    private ArrayList<float[]> velList = new ArrayList<>();
    private ArrayList<int[]> posInit = new ArrayList<>();

    // Width of area
    private int xMax, yMax;

    private final int VEL_X = 10;
    private final int VEL_Y = 5;

    /**
     * This constructor, without any parameters, creates 10 balls to initialize the game.
     */
    public Balls() {
        for (int i = 0; i < 10; ++i) {
            ballsList.add(new Point(i, 0));
            velList.add(new float[]{VEL_X, VEL_Y});
            posInit.add(new int[]{i, 0});
        }
    }

    /**
     * This constructor overloads by taking coordinates of balls
     * @param tab List of coordinates with value on x-axis and y-axis.
     */
    public Balls(int xMax, int yMax, Point... tab) {
        this.xMax = xMax;
        this.yMax = yMax;
        for (Point ball : tab) {
            ballsList.add(ball);
            velList.add(new float[]{VEL_X, VEL_Y});
            posInit.add(new int[]{ball.x, ball.y});
        }
    }

    public ArrayList<Point> getBallsList() {
        return ballsList;
    }

    /**
     * Translate all balls from the coordinates given in parameters.
     * @param dx Value of the translation on x-axis
     * @param dy Value of the translation on y-axis
     */
    public void translate(int dx, int dy) {
        for (Point ball : this.ballsList) {
            ball.translate(dx, dy);
        }
    }

    /**
     * Initialize coordinates of the balls list with the initial the values of the list "posInit".
     * @see Backend
     */
    public void reInit() {
        for (int i = 0; i < this.ballsList.size(); i++) {
            translateInit(ballsList.get(i), posInit.get(i));
        }
    }

    /**
     * Take a step forward of the all the balls in the list of balls.
     * Corresponding to the change applied on the balls with the button next of the simulation.
     * @see Backend
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
     * Initialize the position of the ball given in parameter on the position choose also given in parameter.
     * @param ball Ball which coordinates will change.
     * @param pos Coordinates of the new position.
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
