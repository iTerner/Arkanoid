import biuoop.DrawSurface;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface Animation {
    /**
     * The function do one frame of the game.
     *
     * @param d is the DrawSurface we draw the on frame of the game.
     */
    void doOneFrame(DrawSurface d);

    /**
     * The function checks if the Animation should stop.
     *
     * @return the function returns true if the animation should stop, otherwise false.
     */
    boolean shouldStop();
}
