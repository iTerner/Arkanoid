/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class CollisionInfo {
    private Collidable collidObject;
    private Point collisionPoint;

    /**
     * Constructor.
     *
     * @param c is the collidable object.
     * @param p is the collision point.
     */
    public CollisionInfo(Collidable c, Point p) {
        this.collidObject = c;
        this.collisionPoint = p;
    }
    /**
     * The function returns the point at which the collision occurs.
     *
     * @return the function returns the point at which the collision occurs.
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * The function returns the collidable object involved in the collision.
     *
     * @return the function returns the collidable object involved in the collision..
     */
    public Collidable collisionObject() {
        return this.collidObject;
    }
}
