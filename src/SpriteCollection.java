import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class SpriteCollection {
    private List<Sprite> spriteList;

    /**
     * Constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * the function returns the list of Sprite objects.
     *
     * @return the function returns the list of Sprite objects
     */
    public List<Sprite> getSpriteList() {
        return this.spriteList;
    }

    /**
     * the function add a Sprite object to the list.
     *
     * @param s is the Sprite object we want to add to the list.
     */
    public void addSprite(Sprite s) {
        getSpriteList().add(s);
    }

    /**
     * the function call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    /**
     * the function call drawOn(d) on all sprites.
     *
     * @param d is the surface we want to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : getSpriteList()) {
            s.drawOn(d);
        }
    }
}
