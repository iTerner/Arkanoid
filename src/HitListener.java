/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface HitListener {
    /**
     * The function called whenever the beingHit object is hit.
     *
     * @param beingHit is the collidable object that being hit.
     * @param hitter   is the ball that's doing the hit.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
