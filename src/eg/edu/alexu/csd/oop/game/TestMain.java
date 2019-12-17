package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.object.PairOfPlayers;
import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.world.Level.EasyLevel;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

public class TestMain {
    public static void main(String[] args) {
        GameEngine.start("title", new WorldImp(new PairOfPlayers(0, 800, "/batman.png", "/joker.png"), new EasyLevel()));
    }
}
