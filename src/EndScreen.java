import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class EndScreen implements Animation {
    private int countOfBall;
    private int currentScore;

    /**
     * Constructor.
     *
     * @param score is the current score.
     * @param balls is the count of balls that remained in the game.
     */
    public EndScreen(int balls, int score) {
        this.countOfBall = balls;
        this.currentScore = score;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (d == null) {
            return;
        }
        //if the player lost
        String msg = "Your score is " + this.currentScore;
        if (this.countOfBall == 0) {
            msg = "Game Over. " + msg;
        } else {
            msg = "You Win! " + msg;
        }
        d.setColor(Color.BLACK);
        d.drawText(50, d.getHeight() / 2, msg, 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
