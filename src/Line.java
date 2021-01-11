import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor.
     *
     * @param start is the start point of the line
     * @param end   is the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 is the x value of the start point of the line
     * @param y1 is the y value of the start point of the line
     * @param x2 is the x value of the end point of the line
     * @param y2 is the y value of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The function calculate the length of the line.
     *
     * @return The function returns the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The function calculate the middle point of the line.
     *
     * @return The function returns the middle point of the line
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2.0;
        double midY = (this.start.getY() + this.end.getY()) / 2.0;
        return new Point(midX, midY);
    }

    /**
     * The function returns the start point of the line.
     *
     * @return The function returns the start point of the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * The function returns the end point of the line.
     *
     * @return The function returns the end point of the line.
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function checks if the lines are intersecting according to orientation.
     *
     * @param other is the line we want to check if he intersecting the line
     * @return The function returns true true if the lines intersect, false otherwise.
     */
    public boolean isIntersecting(Line other) {
        //find all the orientations needed for all the cases
        if (other == null) {
            return false;
        }
        int o1 = this.end.orientation(this.start, other.start);
        int o2 = this.end.orientation(this.start, other.end);
        int o3 = other.end.orientation(other.start, this.start);
        int o4 = other.end.orientation(other.start, this.end);
        //the lines are intersecting if the orientation are different
        if (o1 != o2 && o3 != o4) {
            return true;
        }
        //edge cases
        if (o1 == 0 && other.start.onLine(this.start, this.end)) {
            return true;
        }
        if (o2 == 0 && other.end.onLine(this.start, this.end)) {
            return true;
        }
        if (o3 == 0 && this.start.onLine(other.start, other.end)) {
            return true;
        }
        return o4 == 0 && this.end.onLine(other.start, other.end);
    }

    /**
     * The function calculate the intersection point of the 2 lines.
     *
     * @param other is the line we want to find the intersection point with our line.
     * @return The function returns the intersection point if the lines intersect and null otherwise.
     */
    public Point intersectionWith(Line other) {
        //check the edge cases
        if (other == null) {
            return null;
        }
        if (this.equals(other)) {
            return null;
        }
        if (!isIntersecting(other)) {
            return null;
        }
        //find the mathematic presentation of the line
        boolean isOtherLineVertical = false, isThisLineVertical = false;
        double m1 = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
        if (this.start.getX() == this.end.getX()) {
            isThisLineVertical = true;
            m1 = 0;
        }
        double m2 = (other.start.getY() - other.end.getY()) / (other.start.getX() - other.end.getX());
        if (other.start.getX() == other.end.getX()) {
            isOtherLineVertical = true;
            m2 = 0;
        }
        double n1 = -m1 * this.start.getX() + this.start.getY();
        if (m1 == 0) {
            if (this.start.getX() == this.end.getX()) {
                n1 = this.start.getX();
            } else {
                n1 = this.start.getY();
            }
        }
        double n2 = -m2 * other.start.getX() + other.start.getY();
        if (m2 == 0) {
            if (other.start.getX() == other.end.getX()) {
                n2 = other.start.getX();
            } else {
                n2 = other.start.getY();
            }
        }
        //find the x value of the intersection point
        double px = (n2 - n1) / (m1 - m2);
        double py;
        if (m1 == 0 && m2 == 0) {
            if (isOtherLineVertical) {
                px = n2;
                py = n1;
            } else {
                px = n1;
                py = n2;
            }
        } else if (m1 == 0) {
            if (isThisLineVertical) {
                px = n1;
                py = (m2 * px) + n2;
            } else {
                py = n1;
                px = (n1 - n2) / m2;
            }
        } else if (m2 == 0) {
            if (isOtherLineVertical) {
                px = n2;
                py = (m1 * px) + n1;
            } else {
                py = n2;
                px = (n2 - n1) / m1;
            }
        } else {
            py = (m1 * px) + n1;
        }
        //return the intersection point
        return new Point(px, py);
    }

    /**
     * The function check if the 2 lines are equal.
     *
     * @param other is the line we want to check if he is equals to our line.
     * @return The function returns true is the lines are equal, false otherwise.
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return other.start == this.start && other.end == this.end;
    }

    /**
     * <p>
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     * </p>
     *
     * @param rect is the rectangle we want to get the closest intersection point with the line.
     * @return The function returns the closest collision point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        if (rect == null) {
            return null;
        }
        //get all the intersection point of the line with the rectangle
        List<Point> pointList = rect.intersectionPoints(new Line(start(), end()));
        if (pointList.isEmpty()) {
            return null;
        }
        //get the distance with the first intersection point
        double dis1 = start().distance(pointList.get(0));
        if (pointList.size() == 2) {
            if (pointList.get(1).distance(start()) < dis1) {
                //return the second intersection point
                return pointList.get(1);
            }
        }
        //return the first intersection point
        return pointList.get(0);
    }
}
