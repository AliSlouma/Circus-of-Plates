package eg.edu.alexu.csd.oop.game.world.Level;

public class MediumLevel implements LevelState{
    private final int v = 2;
    private final int maxSize = 6;
    private final int maxControllerV = 10;
    private final int X_Coordinate = 1;

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

    @Override
    public int getX_Coordinate() {
        return X_Coordinate;
    }

}
