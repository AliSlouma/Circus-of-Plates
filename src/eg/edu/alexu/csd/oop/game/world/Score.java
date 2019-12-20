package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.object.Players;

import java.util.List;

public class Score {
    private int score;
    private List<Player> players;

    public Score(Players players) {
        this.players = players.getPlayers();
    }

    public int getScore() {
        return 0;
    }
}
