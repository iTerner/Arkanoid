import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private String key;
    private Animation a;
    private boolean isAlreadyPressed;

    /**
     * Constructor.
     *
     * @param animation is the animation.
     * @param key       is the key that stops the animation.
     * @param sensor    is the KeyBoardSensor.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.a = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public boolean shouldStop() {
        return this.keyboard.isPressed(key) && !this.isAlreadyPressed;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (!this.isAlreadyPressed) {
            this.a.doOneFrame(d);
        }
        if (!this.keyboard.isPressed(this.key)) {
            this.isAlreadyPressed = false;
        }
    }
}
