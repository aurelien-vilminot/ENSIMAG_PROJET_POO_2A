import java.awt.Point;
import java.util.ArrayList;

public class Balls {
    private ArrayList<Point> ballsList = new ArrayList<Point>();

    public Balls() {
        for (int i = 0 ; i < 10 ; ++i) {
            ballsList.add(new Point(i, 0));
        }
    }

    void translate(int dx, int dy) {

    }

    void reInit() {

    }

    @Override
    public String toString() {
        String strToReturn = null;
        for (Point ball : this.ballsList) {
            strToReturn = ball.x + ';' + ball.y + "\n";
        }
        return strToReturn;
    }
}
