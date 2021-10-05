import gui.Simulable;

public class BallsSimulator implements Simulable {
    private Balls balls;

    @Override
    public void next() {
        //balls.translate(10, 10);
        System.out.println(balls);
    }

    @Override
    public void restart() {
        balls.reInit();
        System.out.println(balls);
    }
}
