import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public interface LevelInformation {
    /**
     * The function returns the number of balls in the level.
     *
     * @return the function returns the number of balls in the level.
     */
    int numberOfBalls();

    /**
     * The function initializing the balls velocities.
     *
     * @return the function returns a list with all the velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The function returns the paddle speed.
     *
     * @return The function returns the paddle speed.
     */
    int paddleSpeed();

    /**
     * The function returns the paddle width.
     *
     * @return The function returns the paddle width.
     */
    int paddleWidth();

    /**
     * The function returns the level name.
     *
     * @return The function returns the level name.
     */
    String levelName();

    /**
     * The function returns the background of the level.
     *
     * @return The function returns the background of the level.
     */
    Sprite getBackground();

    /**
     * The function returns list of the blocks in the level.
     *
     * @return The function returns list of the blocks in the level.
     */
    List<Block> blocks();

    /**
     * The function returns the number of blocks you need to remove before finishing the level.
     *
     * @return the number of blocks you need to remove.
     */
    int numberOfBlocksToRemove();
}
