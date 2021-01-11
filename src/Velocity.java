/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx is the value of dx.
     * @param dy is the value of dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor.
     *
     * @param angle is the angle of the velocity.
     * @param speed is the 'length' of the velocity.
     * @return the function returns a new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.cos((angle / 180) * Math.PI);
        double dy = speed * Math.sin((angle / 180) * Math.PI);
        return new Velocity(dx, dy);
    }

    /**
     * The function returns the value of dx.
     *
     * @return the function returns the value of dx.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The function returns the value of dy.
     *
     * @return the function returns the value of dy.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The function set a new value to dx.
     *
     * @param dx1 is the new dx value.
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }

    /**
     * The function set a new value to dy.
     *
     * @param dy1 is the new dy value.
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }

    /**
     * The function take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is the point we changes her position according to the velocity
     * @return the function returns new point (x+dx,y+dy).
     */
    public Point applyToPoint(Point p) {
        if (p == null) {
            return null;
        }
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
