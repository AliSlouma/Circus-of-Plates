package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.object.PairOfPlayers;
import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.world.Level.EasyLevel;
import eg.edu.alexu.csd.oop.game.world.Level.HardLevel;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import java.awt.*;

public class TestMain {
    public static void main(String[] args) {
        GameEngine.start("title", new WorldImp(new PairOfPlayers(500, 550, "/batman.png", "/joker.png"), new HardLevel()), Color.black);
    }
}
