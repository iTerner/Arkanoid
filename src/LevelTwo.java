import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class LevelTwo implements LevelInformation {
    private List<Block> blocks;

    /**
     * Constructor.
     */
    public LevelTwo() {
        this.blocks = new ArrayList<Block>();
        createBlocks();
    }

    /**
     * The function creates the blocks for the level.
     */
    public void createBlocks() {
        Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.PINK, Color.CYAN};
        int count = 0, index = 0;
        for (int i = 0; i < 15; i++) {
            Point p = new Point(20 + (51 * i), 350);
            if (i >= 6 && i <= 8) {
                blocks.add(new Block(new Rectangle(p, 50, 30, colors[3])));
                index = 4;
            } else if (i == 14) {
                blocks.add(new Block(new Rectangle(new Point(734, 350), 46, 30, colors[6])));
            } else {
                blocks.add(new Block(new Rectangle(p, 50, 30, colors[index])));
                count++;
                if (count % 2 == 0) {
                    count = 0;
                    index++;
                }
            }
        }
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public int paddleSpeed() {
        return 1;
    }

    @Override
    public int paddleWidth() {
        return 560;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocities.add(Velocity.fromAngleAndSpeed(45 + (10 * i), -3));
        }
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.WHITE);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(new Color(255, 255, 146));
                d.drawCircle(140, 150, 30);
                d.fillCircle(140, 150, 30);
                for (int i = 0; i < 130; i++) {
                    d.drawLine(140, 150, 20 + (i * 5), 350);
                }
                d.setColor(new Color(223, 222, 61));
                d.drawCircle(140, 150, 25);
                d.fillCircle(140, 150, 25);
                d.setColor(Color.YELLOW);
                d.drawCircle(140, 150, 15);
                d.fillCircle(140, 150, 15);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public String levelName() {
        return "Wide Easy";
    }
}
