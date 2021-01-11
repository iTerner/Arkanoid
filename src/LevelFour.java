import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class LevelFour implements LevelInformation {
    private List<Block> blocks;

    /**
     * Constructor.
     */
    public LevelFour() {
        this.blocks = new ArrayList<Block>();
        createBlocks();
    }

    /**
     * The function creates the blocks for the level.
     */
    public void createBlocks() {
        Color[] colors = {Color.GRAY, Color.RED, Color.YELLOW, Color.GREEN, Color.WHITE, Color.PINK, Color.CYAN};
        Point p;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 15; j++) {
                if (j != 14) {
                    p = new Point(20 + (51 * j), 110 + (20 * i));
                    this.blocks.add(new Block(new Rectangle(p, 50, 20, colors[i])));
                } else {
                    p = new Point(734, 110 + (20 * i));
                    this.blocks.add(new Block(new Rectangle(p, 46, 20, colors[i])));
                }
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return 3;
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
        return 120;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        velocities.add(new Velocity(3, -3));
        velocities.add(new Velocity(0, 5));
        velocities.add(new Velocity(-3, -3));
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(new Color(0, 113, 255));
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.WHITE);
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 10; j++) {
                        d.drawLine(105 + (j * 5) + 530 * i, 420 + 110 * i, 85 + (j * 5) + 530 * i, 600);
                    }
                }
                d.setColor(Color.LIGHT_GRAY);
                d.fillCircle(100, 420, 20);
                d.fillCircle(630, 520, 20);
                d.fillCircle(111, 435, 20);
                d.fillCircle(641, 535, 20);
                d.setColor(Color.GRAY);
                d.fillCircle(150, 420, 28);
                d.fillCircle(680, 520, 28);
                d.fillCircle(130, 445, 18);
                d.fillCircle(660, 545, 18);
                d.setColor(new Color(0x7A796B));
                d.fillCircle(115, 410, 23);
                d.fillCircle(645, 510, 23);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public String levelName() {
        return "Final Four";
    }
}
