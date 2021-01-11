import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Constructor.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * The function add the given collidable to the environment.
     *
     * @param c is the collidable object we want to add the the list.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * The function returns the list of the collidable object.
     *
     * @return the function returns the list of the collidable object.
     */
    public List<Collidable> getCollidableList() {
        return collidableList;
    }

    /**
     * <p>
     * Assume an object moving from line.start() to line.end(). If this object will not collide with any of
     * the collidables in this collection, return null. Else, return the information about the closest collision
     * that is going to occur.
     * </p>
     *
     * @param trajectory is the line who represent the move of the ball.
     * @return the function returns the information about the closest collision(if there is one).
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (trajectory == null) {
            return null;
        }
        //check all the collidable objects
        for (Collidable c : getCollidableList()) {
            //get the closest intersection point with the rectangle
            Point p = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            if (p != null) {
                return new CollisionInfo(c, p);
            }
        }
        return null;
    }
}
