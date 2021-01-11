import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment ge;

    /**
     * The constructor for ball.
     *
     * @param center is the center point of the ball.
     * @param r      is the value of the size of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * The constructor for ball.
     *
     * @param x     is the x value of the center point of the ball.
     * @param y     is the y value of the center point of the ball.
     * @param color is the color of the ball.
     * @param r     is the size of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.r = r;
        this.color = color;
    }

    /**
     * accessor.
     *
     * @return the function returns the x value of the center point of the ball.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * accessor.
     *
     * @return the function returns the y value of the center point of the ball.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * accessor.
     *
     * @return the function returns the size of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * accessor.
     *
     * @return the function returns the color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * accessor.
     *
     * @param p is the new center point of the ball.
     */
    public void setCenter(Point p) {
        this.center = p;
    }

    /**
     * The function draw the ball on the given DrawSurface.
     *
     * @param surface is the DrawSurface that we want the ball will be painted on.
     */
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * accessor, the function set a new velocity to the ball.
     *
     * @param velocity is the new velocity of the ball.
     */
    public void setVelocity(Velocity velocity) {
        this.v = velocity;
    }

    /**
     * accessor, the function set a new velocity to the ball.
     *
     * @param dx is the dx value in the new velocity of the ball.
     * @param dy is the dy value in the new velocity of the ball.
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * accessor.
     *
     * @return the function returns the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * accessor.
     *
     * @return the function returns the GameEnvironment of the ball.
     */
    public GameEnvironment getGe() {
        return this.ge;
    }

    /**
     * the function set a new GameEnvironment to the ball.
     *
     * @param gameEnvironment is the new GameEnvironment variable.
     */
    public void setGe(GameEnvironment gameEnvironment) {
        this.ge = gameEnvironment;
    }

    /**
     * the function move the center of the ball after the hit according to the hit location.
     *
     * @param collision    is the collidable object the ball hit.
     * @param intersection is the intersection point.
     */
    public void moveTheBall(Collidable collision, Point intersection) {
        if (collision == null || intersection == null) {
            return;
        }
        Rectangle rec = collision.getCollisionRectangle();
        //check left
        if (intersection.getX() == rec.getUpperLeft().getX()) {
            this.center.setX(intersection.getX() - getSize() - 1);
        }
        //check right
        if (intersection.getX() == rec.getUpperLeft().getX() + rec.getWidth()) {
            this.center.setX(intersection.getX() + getSize() + 1);
        }
        //check top
        if (intersection.getY() == rec.getUpperLeft().getY()) {
            this.center.setY(intersection.getY() - getSize() - 1);
        }
        //check bottom
        if (intersection.getY() == rec.getUpperLeft().getY() + rec.getHeight()) {
            this.center.setY(intersection.getY() + getSize() + 1);
        }
    }

    /**
     * the function move the ball one step.
     */
    public void moveOneStep() {
        Point endPoint = getVelocity().applyToPoint(this.center);
        //create the trajectory line
        Line trajectory = new Line(this.center, endPoint);
        Point intersection;
        for (Collidable c : getGe().getCollidableList()) {
            intersection = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (intersection != null) {
                //get the new velocity according to the hit function
                Velocity newV = c.hit(this, intersection, getVelocity());
                setVelocity(newV);
                //change the center of the ball according to the hit point
                moveTheBall(c, intersection);
                return;
            }
        }
        //if the ball doesn't hit any collidable object, then change the center point tp the end of the trajectory line
        setCenter(endPoint);
    }

    /**
     * the function notify the ball that time had passed.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * the function add the ball to the game.
     *
     * @param g is the Game variable.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
    }

    /**
     * the function remove the ball to the game.
     *
     * @param g is the Game variable.
     */
    public void removeFromGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.removeSprite(this);
    }
}
