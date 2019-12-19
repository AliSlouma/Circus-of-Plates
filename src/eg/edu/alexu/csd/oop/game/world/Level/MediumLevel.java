package eg.edu.alexu.csd.oop.game.world.Level;

public class MediumLevel implements LevelState{
    private final int v = 5;
    private final int maxSize = 13;
    private final int maxControllerV = 10;

    public int getVelocity() {
        return v;
    }

    public int getMaxsize() {
        return maxSize;
    }

    @Override
    public int getControllerVelocity() {
        return maxControllerV;
    }

}
