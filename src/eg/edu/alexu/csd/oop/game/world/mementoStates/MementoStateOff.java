package eg.edu.alexu.csd.oop.game.world.mementoStates;

import eg.edu.alexu.csd.oop.game.world.WorldImp;

public class MementoStateOff implements MementoState {
    private WorldImp.Memento myMemento;
    private int worldCounter;
    private int size;
    public MementoStateOff(WorldImp.Memento memento){
        myMemento=memento;
        size=myMemento.getShots().size();
        worldCounter=size-2;
    }
    @Override
    public void execute(Boolean timeout) {
        if(worldCounter>= 0){
            System.out.println("setting world");
            System.out.println("size = " +  size + " world counter = "+worldCounter);
            myMemento.setWorld(worldCounter);
            worldCounter--;
        }
    }
}
