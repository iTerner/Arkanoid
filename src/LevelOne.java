import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class LevelOne implements LevelInformation {
    private List<Block> blocks;

    /**
     * Constructor.
     */
    public LevelOne() {
        this.blocks = new ArrayList<Block>();
        createBlocks();
    }

    /**
     * The function creates the blocks for the level.
     */
    public void createBlocks() {
        blocks.add(new Block(new Rectangle(new Point(380, 120), 40, 40, Color.RED)));
    }

    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.blocks.size();
    }

    @Override
    public int paddleSpeed() {
        return 5;
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
        velocities.add(new Velocity(0, -6));
        return velocities;
    }

    @Override
    public Sprite getBackground() {
        return new Sprite() {
            @Override
            public void drawOn(DrawSurface d) {
                d.setColor(Color.BLACK);
                d.fillRectangle(0, 0, 800, 600);
                d.setColor(Color.BLUE);
                d.drawCircle(400, 140, 100);
                d.drawCircle(400, 140, 70);
                d.drawCircle(400, 140, 40);
                d.drawLine(400, 140, 400, 40);
                d.drawLine(400, 140, 400, 240);
                d.drawLine(400, 140, 300, 140);
                d.drawLine(400, 140, 500, 140);
            }

            @Override
            public void timePassed() {

            }
        };
    }

    @Override
    public String levelName() {
        return "Direct Hit";
    }
}
