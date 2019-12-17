package eg.edu.alexu.csd.oop.game.world.Level;

public class EasyLevel implements LevelState {

    private final int v = 2;
    private final int maxSize = 5;

    public int getVelocity() {
        return v;
    }

    public int getMaxsize() {
        return maxSize;
    }
}
