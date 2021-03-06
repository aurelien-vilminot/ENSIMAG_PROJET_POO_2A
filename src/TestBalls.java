import java.awt.*;

/**
 * Class to test the class Balls.
 */
public class TestBalls {
    public static void main(String[] args) {
        Balls balls = new Balls(10, 10,
                new Point(1,1),
                new Point(1, 5),
                new Point(4, 8),
                new Point(8,7)
        );

        System.out.println(balls);
        balls.translate(2,2);
        System.out.println(balls);
        balls.reInit();
        System.out.println(balls);
    }
}
