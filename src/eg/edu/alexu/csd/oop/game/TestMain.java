package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.world.Level.EasyLevel;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

public class TestMain {
    public static void main(String[] args) {
        GameEngine.start("title", new WorldImp(new Player(500, 400, "/batman.png"), new EasyLevel()));
    }
}
