import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlock;
    private Counter remainingBall;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * Constructor.
     *
     * @param ar    is the animation runner.
     * @param ks    is the KeyBoardSensor.
     * @param lvl   is the level information.
     * @param score is the current score.
     */
    public GameLevel(LevelInformation lvl, AnimationRunner ar, KeyboardSensor ks, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.running = true;
        this.keyboard = ks;
        this.runner = ar;
        this.levelInfo = lvl;
        this.score = score;
    }

    /**
     * The function returns the GameEnvironment object of the game.
     *
     * @return the function returns the GameEnvironment object of the game.
     */
    public GameEnvironment getEnvironment() {
        return environment;
    }

    /**
     * The function returns the SpriteCollection object of the game.
     *
     * @return the function returns the SpriteCollection object of the game.
     */
    public SpriteCollection getSprites() {
        return sprites;
    }

    /**
     * The function add a new collidable object to the game.
     *
     * @param c is the collidable object we want to add to the GameEnvironment of the game.
     */
    public void addCollidable(Collidable c) {
        getEnvironment().addCollidable(c);
    }

    /**
     * The function add a new Sprite object to the game.
     *
     * @param s is the Sprite object we want to add to the SpriteCollection of the game.
     */
    public void addSprite(Sprite s) {
        getSprites().addSprite(s);
    }

    /**
     * The function initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        BlockRemover br = new BlockRemover(this, new Counter(0));
        BallRemover ballRemover = new BallRemover(this, new Counter(0));
        //create the edge blocks and add them to the game
        double edgeBlockWidth = 20;
        Block b = new Block(new Rectangle(new Point(0, 20), 800, 20, Color.GRAY));
        b.setHitListeners(new ArrayList<HitListener>());
        b.addToGame(this);
        b = new Block(new Rectangle(new Point(0, 40), 20, 560, Color.GRAY));
        b.setHitListeners(new ArrayList<HitListener>());
        b.addToGame(this);
        b = new Block(new Rectangle(new Point(780, 40), 20, 560, Color.GRAY));
        b.setHitListeners(new ArrayList<HitListener>());
        b.addToGame(this);
        b = new Block(new Rectangle(new Point(0, 605), 800, 10, Color.GRAY));
        b.setHitListeners(new ArrayList<HitListener>());
        b.addHitListener(ballRemover);
        b.addToGame(this);
        ScoreTrackingListener scl = new ScoreTrackingListener(this.score);
        Block temp = new Block(new Rectangle(new Point(0, 0), 800, 20, Color.WHITE));
        ScoreIndicator si = new ScoreIndicator(temp, this.score);

        //add the blocks to the game
        List<Block> blocks = this.levelInfo.blocks();
        br.getRemainingBlocks().increase(this.levelInfo.numberOfBlocksToRemove());
        for (Block block : blocks) {
            block.setHitListeners(new ArrayList<HitListener>());
            block.addHitListener(br);
            block.addHitListener(scl);
            block.addToGame(this);
        }
        si.addToGame(this);
        setRemainingBlock(br.getRemainingBlocks());
        setRemainingBall(ballRemover.getRemainingBalls());

        //create the paddle and add him to the game
        Point p = new Point(400 - this.levelInfo.paddleWidth() / 2, 560);
        Rectangle rec = new Rectangle(p, this.levelInfo.paddleWidth(), 20, Color.YELLOW);
        Paddle paddle = new Paddle(runner.getGui(), rec, 20, this.levelInfo.paddleSpeed());
        paddle.setKeyboard(keyboard);
        paddle.addToGame(this);

        //add the balls to the game
        List<Ball> balls = new ArrayList<Ball>();
        this.remainingBall.increase(this.levelInfo.numberOfBalls());
        List<Velocity> velocities = this.levelInfo.initialBallVelocities();
        for (Velocity v : velocities) {
            Ball ball = new Ball(new Point(399, 550), 5, Color.WHITE);
            ball.setVelocity(v);
            ball.setGe(this.environment);
            ball.addToGame(this);
            balls.add(ball);
        }

        LevelNameLabel levelNameLabel = new LevelNameLabel(this.levelInfo.levelName());
        levelNameLabel.addToGame(this);
    }

    /**
     * The function run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites,
                this.levelInfo.getBackground()));
        this.runner.run(this);
    }

    /**
     * The function remove collidable object from the GameEnvironment.
     *
     * @param c is the collidable object we want to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidableList().remove(c);
    }

    /**
     * The function remove sprite object from the SpriteCollection.
     *
     * @param s is the sprite object we want to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSpriteList().remove(s);
    }

    /**
     * The function set a new Counter for the remaining blocks in the game.
     *
     * @param remainingBlocks is the new Counter for the remaining blocks in the game.
     */
    public void setRemainingBlock(Counter remainingBlocks) {
        this.remainingBlock = remainingBlocks;
    }

    /**
     * The function set a new Counter for the remaining balls in the game.
     *
     * @param remainingBalls is the new Counter for the remaining balls in the game.
     */
    public void setRemainingBall(Counter remainingBalls) {
        this.remainingBall = remainingBalls;
    }

    /**
     * The function returns the counter of the remaining balls.
     *
     * @return the function returns the counter of the remaining balls.
     */
    public Counter getRemainingBall() {
        return remainingBall;
    }

    /**
     * The function returns the counter of the remaining blocks.
     *
     * @return the function returns the counter of the remaining blocks.
     */
    public Counter getRemainingBlock() {
        return remainingBlock;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (d == null) {
            return;
        }
        Sprite background = this.levelInfo.getBackground();
        background.drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.remainingBall.getValue() == 0 || this.remainingBlock.getValue() == 0) {
            this.running = false;
            if (this.remainingBlock.getValue() == 0) {
                this.score.increase(100);
            }
        }
        if (this.keyboard.isPressed("p")) {
            PauseScreen p = new PauseScreen();
            KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, p);
            this.runner.run(k);
        }

    }

    /**
     * The function returns the current score.
     *
     * @return the function returns the current score.
     */
    public int getScore() {
        return this.score.getValue();
    }
}
