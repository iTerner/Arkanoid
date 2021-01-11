import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ido Terner 325132850
 * @version 1.0
 * @since 2020-06-14
 */
public class Ass6Game {
    /**
     * The function checks if the string is a digit or not.
     *
     * @param str is the string.
     * @return the function returns true if the string is a digit and false otherwise.
     */
    public static boolean isDigit(String str) {
        if (str == null) {
            return false;
        }
        try {
            int number = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return str.length() == 1;
    }

    /**
     * The function add the level to the level's list according to the level number.
     *
     * @param list        is the list of levels
     * @param levelNumber is the number of the level
     */
    public static void addLevel(List<LevelInformation> list, int levelNumber) {
        if (levelNumber == 1) {
            list.add(new LevelOne());
        } else if (levelNumber == 2) {
            list.add(new LevelTwo());
        } else if (levelNumber == 3) {
            list.add(new LevelThree());
        } else if (levelNumber == 4) {
            list.add(new LevelFour());
        }
    }

    /**
     * The function create, initialize and run the game.
     *
     * @param args is the optional levels that the user entered.
     */
    public static void main(String[] args) {
        AnimationRunner ar = new AnimationRunner(new GUI("game", 800, 600));
        KeyboardSensor key = ar.getGui().getKeyboardSensor();
        GameFlow flow = new GameFlow(ar, key);
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
        levels.add(new LevelOne());
        levels.add(new LevelTwo());
        levels.add(new LevelThree());
        levels.add(new LevelFour());
        //add all the level we get from the user
        List<LevelInformation> temp = new ArrayList<LevelInformation>();
        for (String arg : args) {
            if (isDigit(arg)) {
                addLevel(temp, Integer.parseInt(arg));
            }
        }
        //run the game
        if (temp.isEmpty()) {
            flow.runLevels(levels);
        } else {
            flow.runLevels(temp);
        }
        //close the gui
        ar.getGui().close();
    }
}
