import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private List<HitListener> listenersList;

    /**
     * Constructor.
     *
     * @param rectangle is the rectangle who represent the block.
     */
    public Block(Rectangle rectangle) {
        this.rec = rectangle;
    }

    /**
     * The function returns the rectangle of the block.
     *
     * @return the function returns the rectangle of the block.
     */
    public Rectangle getRec() {
        return this.rec;
    }

    /**
     * The function returns the collision rectangle.
     *
     * @return the function returns the collision rectangle.
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    /**
     * The function calculates and returns the new velocity after the hit.
     *
     * @param currentVelocity is the current velocity of the moving object.
     * @param collisionPoint  is the collision point.
     * @param hitter is the hitter ball.
     * @return the function returns the new velocity of moving object.
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        if (collisionPoint == null || currentVelocity == null) {
            return null;
        }
        this.notifyHit(hitter);
        //check X axis
        if (collisionPoint.getX() == getRec().getUpperLeft().getX()
                || collisionPoint.getX() == getRec().getUpperLeft().getX() + getRec().getWidth()) {
            return new Velocity(-currentVelocity.getDx(), currentVelocity.getDy());
        }
        return new Velocity(currentVelocity.getDx(), -currentVelocity.getDy());
    }

    /**
     * The function draw the block.
     *
     * @param surface is the surface we want to draw the block on.
     */
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        this.rec.drawOn(surface);
    }

    /**
     * The function notify that time has passed.
     */
    public void timePassed() {
    }

    /**
     * The function add the block to the game.
     *
     * @param g is the game we want to add the block to it.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
        g.addCollidable(this);

    }

    /**
     * The function remove the block from game.
     *
     * @param game is the game.
     */
    public void removeFromGame(GameLevel game) {
        if (game == null) {
            return;
        }
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * The function set a new HitListeners to the block.
     *
     * @param hitListeners is the new HitListeners.
     */
    public void setHitListeners(List<HitListener> hitListeners) {
        this.listenersList = hitListeners;
    }

    /**
     * The function notify all the listeners that a hit just occurred.
     *
     * @param hitter is the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.listenersList);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.listenersList.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.listenersList.remove(hl);
    }
}
