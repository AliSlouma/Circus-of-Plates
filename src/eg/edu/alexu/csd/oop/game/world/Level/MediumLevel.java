package eg.edu.alexu.csd.oop.game.world.Level;

public class MediumLevel implements LevelState{
    private final int v = 15;
    private final int maxSize = 30;

    public int getVelocity() {
        return v;
    }

    public int getMaxsize() {
        return maxSize;
    }


}
