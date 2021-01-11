import biuoop.DrawSurface;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface Sprite {
    /**
     * the function draw the sprite to the screen.
     *
     * @param d is the surface we want to draw the sprite on.
     */
    void drawOn(DrawSurface d);

    /**
     * the function notify the sprite that time has passed.
     */
    void timePassed();
}
