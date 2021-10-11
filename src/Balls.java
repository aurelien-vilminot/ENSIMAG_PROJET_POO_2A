import java.awt.Point;
import java.util.ArrayList;
import java.lang.Math;

public class Balls {
    private ArrayList<Point> ballsList = new ArrayList<Point>();
    private ArrayList<float[]> velList = new ArrayList<float[]>();
    private ArrayList<int[]> posInit = new ArrayList<int[]>();

    // Def des vélocité
    private int velX = 10;
    private int velY = 5;

    // Constructeur 1
    public Balls() {
        for (int i = 0; i < 10; ++i) {
            ballsList.add(new Point(i, 0));
            velList.add(new float[]{velX, velY});
            posInit.add(new int[]{i, 0});
        }
    }

    // Constructeur 2
    public Balls(Point... tab) {
        for (Point ball : tab) {
            ballsList.add(ball);
            velList.add(new float[]{velX, velY});
            posInit.add(new int[]{ball.x, ball.y});
        }
    }

    public ArrayList<Point> getBallsList() {
        return ballsList;
    }

    // Translate les balles d'une valeur donnée
    void translate(int dx, int dy) {
        for (Point ball : this.ballsList) {
            ball.translate(dx, dy);
        }
    }

    // Initialisation des balles
    void reInit() {
        for (int i = 0; i < this.ballsList.size(); i++) {
            translateInit(ballsList.get(i), posInit.get(i));
        }
    }

    // On fait bouger les balles
    void step(float dt, int width, int height) {
        for (int i = 0; i < this.ballsList.size(); i++) {
            Point pos = this.ballsList.get(i);
            float[] vel = this.velList.get(i);
            int dx = Math.round(dt * vel[0]);
            int dy = Math.round(dt * vel[1]);

            // Si on tape les murs, on oppose la vélocité des balles
            if (pos.x + dx > width || pos.x + dx < 0) {
                vel[0] = -vel[0];
                dx = -dx;
            }
            if (pos.y + dy > height || pos.y + dy < 0) {
                vel[1] = -vel[1];
                dy = -dy;
            }
            pos.translate(dx, dy);
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
