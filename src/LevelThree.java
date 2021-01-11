import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class LevelThree implements LevelInformation {
    private List<Block> blocks;

    /**
     * Constructor.
     */
    public LevelThree() {
        this.blocks = new ArrayList<Block>();
        createBlocks();
    }

    /**
     * The function creates the blocks for the level.
     */
    public void createBlocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10 - i; j++) {
                Point p = new Point(780 - (50 * (j + 1)), 150 + (30 * i));
                this.blocks.add(new Block(new Rectangle(p, 50, 30, colors[i])));
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public int paddleSpeed() {
        return 6;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(4, -4));
        velocities.add(new Velocity(-4, -4));
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(15, 106, 0));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLACK);
                d.fillRectangle(80, 450, 100, 150);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        d.fillRectangle(85 + (20 * j), 460 + (30 * i), 10, 25);
                    }
                }
                d.setColor(Color.BLACK);
                d.fillRectangle(115, 400, 30, 50);
                d.fillRectangle(122, 230, 15, 170);
                d.setColor(Color.ORANGE);
                d.fillCircle(130, 215, 15);
                d.setColor(Color.RED);
                d.fillCircle(130, 215, 10);
                d.setColor(Color.WHITE);
                d.fillCircle(130, 215, 5);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public String levelName() {
        return "Green 3";
    }
}
