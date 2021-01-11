import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class PauseScreen implements Animation {
    /**
     * Constructor.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (d == null) {
            return;
        }
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
