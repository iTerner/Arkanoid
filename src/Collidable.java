/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface Collidable {
    /**
     * The function return the "collision shape" of the object.
     *
     * @return the function return the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * The function notify the object that we collided with it at collisionPoint with a given velocity.
     *
     * @param collisionPoint  is the collision point.
     * @param currentVelocity is the current velocity of the moving object.
     * @param hitter          is the ball who hit.
     * @return the function returns is the new velocity expected after the hit.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
