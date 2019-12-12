package eg.edu.alexu.csd.oop.game.world.Level;

public class HardLevel implements LevelState {

    private final int v = 20;
    private final int maxSize = 40;

    public int getVelocity() {
        return v;
    }

    public int getMaxsize() {
        return maxSize;
    }

}
