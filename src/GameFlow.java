import biuoop.KeyboardSensor;

import java.util.List;
/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class GameFlow {
    private KeyboardSensor ks;
    private AnimationRunner ar;
    private int currentScore;
    /**
     * Constructor.
     *
     * @param ks is the KeyBoardSensor.
     * @param ar is the Animation runner.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.currentScore = 0;
    }
    /**
     * The function runs all the levels in the levels list.
     *
     * @param levels is the list of levels
     */
    public void runLevels(List<LevelInformation> levels) {
        if (levels == null) {
            return;
        }
        EndScreen e;
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, ar, ks, new Counter(this.currentScore));

            level.initialize();

            while (level.getRemainingBall().getValue() != 0 && level.getRemainingBlock().getValue() != 0) {
                level.run();
            }
            this.currentScore = level.getScore();
            if (level.getRemainingBall().getValue() == 0) {
                e = new EndScreen(0, currentScore);
                KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, e);
                this.ar.run(k);
                this.ar.getGui().close();
                break;
            }
        }
        //we know that the player won
        e = new EndScreen(1, currentScore);
        KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY, e);
        this.ar.run(k);
        this.ar.getGui().close();
    }
}
