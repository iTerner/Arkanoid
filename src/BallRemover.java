/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class BallRemover implements HitListener {
    private GameLevel g;
    private Counter remainingBalls;

    /**
     * constructor.
     *
     * @param game    is the Game variable.
     * @param counter is the remaining balls counter.
     */
    public BallRemover(GameLevel game, Counter counter) {
        this.g = game;
        this.remainingBalls = counter;
    }

    /**
     * The function remove the beingHit block from the game.
     *
     * @param hitter   is the hitter ball.
     * @param beingHit is the block we will remove from the game.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == null || hitter == null) {
            return;
        }
        //remove the Ball from the game
        hitter.removeFromGame(this.g);
        //change the current remaining balls
        this.remainingBalls.decrease(1);
    }

    /**
     * The function return the remaining balls counter.
     *
     * @return the function return the remaining balls counter.
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }
}
