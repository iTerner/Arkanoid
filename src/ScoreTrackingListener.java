/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructor for ScoreTrackingListener.
     *
     * @param scoreCounter is the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }

    /**
     * The function returns the score counter.
     *
     * @return the function returns the score counter.
     */
    public Counter getCurrentScore() {
        return currentScore;
    }
}
