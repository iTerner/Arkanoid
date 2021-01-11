import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class ScoreIndicator implements Sprite {
    private Block blk;
    private Counter currentScore;

    /**
     * Constructor for ScoreIndicator.
     *
     * @param block is the block that contains the text.
     * @param score is the score Counter.
     */
    public ScoreIndicator(Block block, Counter score) {
        this.blk = block;
        this.currentScore = score;
    }


    @Override
    public void drawOn(DrawSurface d) {
        if (d == null) {
            return;
        }
        this.blk.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(300, 15, "Score: " + this.currentScore.getValue(), 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * The function add ScoreIndicator to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        if (g == null) {
            return;
        }
        g.addSprite(this);
    }

}
