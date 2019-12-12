package eg.edu.alexu.csd.oop.game.world.Level;

public class LevelFactory {

    LevelState currentState;

    public LevelFactory(LevelState currentState) {

        if(currentState.getClass() == EasyLevel.class)
            this.currentState = new EasyLevel();
        if(currentState.getClass() == MediumLevel.class)
            this.currentState = new MediumLevel();
        if(currentState.getClass() == HardLevel.class)
            this.currentState = new HardLevel();
    }

    public LevelState getState(){
        return this.currentState;
    }
}
