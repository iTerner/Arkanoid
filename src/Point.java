/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Point {
    private double x;
    private double y;

    /**
     * The constructor for Point.
     *
     * @param x is the value of x.
     * @param y is the value of y.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * The function finds the orientation with 2 other point.
     *
     * @param other is the point we want to calculate the distance with.
     * @return the function returns the distance between the 2 points, if other is null the function returns -1
     */
    public double distance(Point other) {
        if (other == null) {
            return -1;
        }
        return Math.sqrt(Math.pow(other.getX() - this.x, 2) + Math.pow(other.getY() - this.y, 2));
    }

    /**
     * The function finds the orientation with 2 other point.
     *
     * @param other is the point we want to check if it equal to our point.
     * @return the function returns true if the points are equal and false otherwise.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (other.getX() == this.x) && (other.getY() == this.y);
    }

    /**
     * The function returns the value of x.
     *
     * @return the function returns the value of x.
     */
    public double getX() {
        return this.x;
    }

    /**
     * The function returns the value of y.
     *
     * @return the function returns the value of y.
     */
    public double getY() {
        return this.y;
    }

    /**
     * The function set new value to x.
     *
     * @param x1 is the new x value of the point.
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * The function set new value to y.
     *
     * @param y1 is the new y value of the point.
     */
    public void setY(double y1) {
        this.y = y1;
    }

    /**
     * Given three points the function check if the point in on the line 'pr'.
     *
     * @param start is the start point of the line 'pr'.
     * @param end   is the end point of the line 'pr'.
     * @return the function returns true if the point is on the line and false otherwise.
     */
    public boolean onLine(Point start, Point end) {
        if (start == null || end == null) {
            return false;
        }
        if (this.x <= Math.max(start.getX(), end.getX()) && this.x >= Math.min(start.getX(), end.getX())) {
            return this.y <= Math.max(start.getY(), end.getY()) && this.y >= Math.min(start.getY(), end.getY());
        }
        return false;
    }


    /**
     * The function finds the orientation with 2 other point.
     *
     * @param p is one of the 2 points that we want to find the orientation with.
     * @param q is one of the 2 points that we want to find the orientation with.
     * @return the function returns 0 if the points are colinear, 1 if clockwise and 2 CounterClockWise
     */
    public int orientation(Point p, Point q) {
        if (p == null || q == null) {
            return -1;
        }
        //calculate the orientation between p, q, the
        double val = (this.y - p.getY()) * (q.getX() - this.x) - (this.x - p.getX()) * (q.getY() - this.y);
        if (val == 0) {
            return 0;
        }
        return (val > 0) ? 1 : 2;
    }
}
