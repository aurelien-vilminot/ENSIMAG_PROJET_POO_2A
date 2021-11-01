import gui.GraphicalElement;

import java.awt.*;

/**
 * Class Triangle to represents boids by triangle, it implements a GraphicalElement
 * of the inteface Simulable.
 */
public class Triangle implements GraphicalElement {
    private int[] x = new int[3];
    private int[] y = new int[3];
    private Color drawColor;
    private Color fillColor;
    private float borderWidth;

    /**
     * Isosceles triangle constructor
     *
     * @param startPoint  : point at the base of the triangle
     * @param endPoint    : point at the tip of the triangle
     * @param drawColor   : outline color (can be null)
     * @param fillColor   : fill color (can be null)
     * @param borderWidth : border width
     */
    public Triangle(Vector startPoint, Vector endPoint, Color drawColor, Color fillColor, float borderWidth) {
        super();

        float distance = Vector.distance(startPoint, endPoint);
        double angle = -Math.atan2(endPoint.getY() - startPoint.getY(), endPoint.getX() - startPoint.getX());
        double sin = Math.sin(angle) * (distance / 2);
        double cos = Math.cos(angle) * (distance / 2);
        this.x[0] = (int) endPoint.getX();
        this.y[0] = (int) endPoint.getY();
        this.x[1] = (int) (startPoint.getX() + sin);
        this.y[1] = (int) (startPoint.getY() + cos);
        this.x[2] = (int) (startPoint.getX() - sin);
        this.y[2] = (int) (startPoint.getY() - cos);

        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.borderWidth = borderWidth;
    }

    /**
     * Isosceles triangle representing a boid constructor
     *
     * @param boidSize    : size of the boid (height of the triangle)
     * @param position    : position of the boid (tip of the triangle)
     * @param velocity    : velocity of the boid (height direction of the triangle)
     * @param drawColor   : outline color (can be null)
     * @param fillColor   : fill color (can be null)
     * @param borderWidth : border width
     */
    public Triangle(int boidSize, Vector position, Vector velocity, Color drawColor, Color fillColor, float borderWidth) {
        super();

        double angleV = Math.atan2(velocity.getY(), velocity.getX());
        double cosV = boidSize * Math.cos(angleV);
        double sinV = boidSize * Math.sin(angleV);
        Vector startPoint = new Vector((int) (position.getX() - cosV), (int) (position.getY() - sinV));
        Vector endPoint = new Vector(position.getX(), position.getY());

        float distance = Vector.distance(startPoint, endPoint);
        double angle = -Math.atan2(endPoint.getY() - startPoint.getY(), endPoint.getX() - startPoint.getX());
        double sin = Math.sin(angle) * (distance / 2);
        double cos = Math.cos(angle) * (distance / 2);
        this.x[0] = (int) endPoint.getX();
        this.y[0] = (int) endPoint.getY();
        this.x[1] = (int) (startPoint.getX() + sin);
        this.y[1] = (int) (startPoint.getY() + cos);
        this.x[2] = (int) (startPoint.getX() - sin);
        this.y[2] = (int) (startPoint.getY() - cos);

        this.drawColor = drawColor;
        this.fillColor = fillColor;
        this.borderWidth = borderWidth;
    }

    public void paint(Graphics2D g2d) {
        Stroke currentStroke = g2d.getStroke();
        g2d.setStroke(new BasicStroke(this.borderWidth));
        Color current = g2d.getColor();
        if (this.fillColor != null) {
            g2d.setColor(this.fillColor);
            g2d.fillPolygon(x, y, 3);
        }
        if (this.drawColor != null) {
            g2d.setColor(this.drawColor);
            g2d.drawPolygon(x, y, 3);
        }
        g2d.drawOval((int) (x[0] - borderWidth), (int) (y[0] - borderWidth), (int) borderWidth * 2, (int) borderWidth * 2);
        g2d.setColor(current);
        g2d.setStroke(currentStroke);
    }
}
