package eg.edu.alexu.csd.oop.game.world.Level;

public class HardLevel implements LevelState {

    private final int v = 7;
    private final int maxSize = 19;

    public int getVelocity() {
        return v;
    }

    public int getMaxsize() {
        return maxSize;
    }
}
