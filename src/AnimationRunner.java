import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;
    /**
     * Constructor.
     *
     * @param gui is the GUI of the game.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }
    /**
     * The function runs the animation.
     *
     * @param animation is the animation the function runs.
     */
    public void run(Animation animation) {
        if (animation == null) {
            return;
        }
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
    /**
     * The function returns the gui.
     *
     * @return The function returns the gui.
     */
    public GUI getGui() {
        return gui;
    }
}
