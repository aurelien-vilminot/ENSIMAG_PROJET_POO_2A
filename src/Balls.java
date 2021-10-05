import java.awt.Point;
import java.util.ArrayList;

public class Balls {
    private ArrayList<Point> ballsList = new ArrayList<Point>();
    private ArrayList<int[]> posInit = new ArrayList<int[]>();

    public Balls() {
        for (int i = 0; i < 10; ++i) {
            ballsList.add(new Point(i, 0));
            posInit.add(new int[]{i, 0});
        }
    }

    public Balls(Point... tab) {
        for (Point ball : tab) {
            ballsList.add(ball);
            posInit.add(new int[]{ball.x, ball.y});
        }
    }

    void translate(int dx, int dy) {
        for (Point ball : this.ballsList) {
            ball.translate(dx, dy);
        }
    }

    void reInit() {
        for (int i = 0; i < this.ballsList.size(); i++) {
            translateInit(ballsList.get(i), posInit.get(i));
        }
    }

    private void translateInit(Point ball, int[] pos) {
        ball.move(pos[0], pos[1]);
    }

    @Override
    public String toString() {
        String strToReturn = "";
        for (Point ball : this.ballsList) {
            strToReturn += ball.x + ";" + ball.y + "\n";
        }
        return strToReturn;
    }
}
