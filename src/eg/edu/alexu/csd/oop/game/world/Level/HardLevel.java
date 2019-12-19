package eg.edu.alexu.csd.oop.game.world.Level;

public class HardLevel implements LevelState {

    private final int v = 6;
    private final int maxSize = 18;
    private final int maxControllerV = 9;
    private final int X_Coordinate = 8;

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
