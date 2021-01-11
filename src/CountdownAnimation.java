import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sprite background;
    private int currentNumber;

    /**
     * Constructor.
     *
     * @param countFrom    is the number we want to start the count from.
     * @param gameScreen   is the screen of the level.
     * @param numOfSeconds is the number of seconds the countdown last.
     * @param b            is the background of the level.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen, Sprite b) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.background = b;
        this.currentNumber = 0;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (d == null) {
            return;
        }
        //draw the background and the level screen
        this.background.drawOn(d);
        this.gameScreen.drawAllOn(d);
        //print the countdown
        d.setColor(Color.WHITE);
        d.drawText(d.getWidth() / 2, 450, (this.countFrom - this.currentNumber) + "", 32);
        //timing
        double sec = (this.numOfSeconds / this.countFrom);
        Sleeper sleeper = new Sleeper();
        if (this.currentNumber != 0) {
            sleeper.sleepFor((int) (1000 * sec));
        }
        this.currentNumber++;
    }

    @Override
    public boolean shouldStop() {
        return this.countFrom == this.currentNumber - 1;
    }

}
