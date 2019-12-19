package eg.edu.alexu.csd.oop.game.world.mementoStates;

import eg.edu.alexu.csd.oop.game.world.WorldImp;

public class MementoStateOn implements MementoState {
    private WorldImp.Memento myMemento;
    public MementoStateOn(WorldImp.Memento memento){
        myMemento=memento;
    }
    @Override
    public void execute(Boolean timeout) {
        if(timeout){
            myMemento.setState(new MementoStateOff(myMemento));
            myMemento.addWorld(null,timeout);
        }

    }
}
