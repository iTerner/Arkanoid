import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class LevelNameLabel implements Sprite {
    private String text;

    /**
     * Constructor.
     *
     * @param s is the name of the level.
     */
    public LevelNameLabel(String s) {
        this.text = s;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (d == null) {
            return;
        }
        d.setColor(Color.BLACK);
        d.drawText(540, 15, "Level Name: " + this.text, 15);
    }

    @Override
    public void timePassed() {

    }

    /**
     * The function add LevelNameLabel to the game.
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
