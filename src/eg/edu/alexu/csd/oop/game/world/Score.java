package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.object.Players;

public class Score {
    private Players player;

    Score(Players player) {
        this.player = player;
    }

    public int getScore() {
        return this.player.getScore();
    }
}
