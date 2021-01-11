import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructor.
     *
     * @param upperLeft is the upper left point of the rectangle.
     * @param width     is the width of the rectangle.
     * @param height    is the height of the rectangle.
     * @param c         is the color of the rectangle.
     */
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height, Color c) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * The function returns a (possibly empty) List of intersection points with the specified line.
     *
     * @param line is the line we want to find the intersection point with the rectangle.
     * @return the function returns a (possibly empty) List of intersection points with the specified line.
     */
    public List<Point> intersectionPoints(Line line) {
        if (line == null) {
            return new ArrayList<Point>();
        }
        //transform all the rectangle ribs into Lines
        Point p1 = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY() + getHeight());
        Point p2 = new Point(getUpperLeft().getX(), getUpperLeft().getY() + getHeight());
        Point p3 = new Point(getUpperLeft().getX() + getWidth(), getUpperLeft().getY());
        List<Line> lineList = new ArrayList<Line>();
        lineList.add(new Line(getUpperLeft(), p2));
        lineList.add(new Line(getUpperLeft(), p3));
        lineList.add(new Line(p1, p2));
        lineList.add(new Line(p1, p3));
        List<Point> pointList = new ArrayList<Point>(), tempList = new ArrayList<Point>();
        Point intersection;
        for (Line l : lineList) {
            if (l.isIntersecting(line)) {
                //add the intersection point to the list
                intersection = l.intersectionWith(line);
                tempList.add(intersection);
                //delete duplicated points (if there is)
                if (!pointList.contains(intersection)) {
                    pointList.add(intersection);
                }
            }
        }
        //return the list of intersection points
        return pointList;
    }

    /**
     * The function returns the width of the rectangle.
     *
     * @return the function returns the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The function returns the height of the rectangle.
     *
     * @return the function returns the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function returns the upper-left point of the rectangle.
     *
     * @return the function returns the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * The function draw the rectangle.
     *
     * @param surface is the surface we want to draw the rectangle on.
     */
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) width, (int) height);
        surface.setColor(this.color);
        surface.fillRectangle((int) getUpperLeft().getX(), (int) getUpperLeft().getY(), (int) width, (int) height);
    }
}
