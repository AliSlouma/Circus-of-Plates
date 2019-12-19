package eg.edu.alexu.csd.oop.game.world.Level;

public class OffLevel implements LevelState {
    @Override
    public int getVelocity() {
        return 0;
    }

    @Override
    public int getMaxsize() {
        return 0;
    }

    @Override
    public int getControllerVelocity() {
        return 0;
    }
}
