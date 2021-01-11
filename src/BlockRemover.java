/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class BlockRemover implements HitListener {
    private GameLevel g;
    private Counter remainingBlocks;
    /**
     * constructor.
     *
     * @param game    is the Game variable.
     * @param removedBlocks is the remaining blocks counter.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.g = game;
        this.remainingBlocks = removedBlocks;
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
        //remove the block from the game
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.g);
        //change the current remaining blocks
        this.remainingBlocks.decrease(1);
    }
    /**
     * The function return the remaining blocks counter.
     *
     * @return the function return the remaining blocks counter.
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }
}
