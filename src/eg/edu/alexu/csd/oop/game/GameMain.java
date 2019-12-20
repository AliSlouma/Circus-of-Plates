package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.GUI.View;
import eg.edu.alexu.csd.oop.game.world.Level.LevelFactory;

public class GameMain {
    public static void main(String[] args) {
        View view = new View();
        LevelFactory level = new LevelFactory();
        Controller controller = new Controller(view ,level);

    }
}
