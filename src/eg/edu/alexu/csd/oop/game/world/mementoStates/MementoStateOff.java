package eg.edu.alexu.csd.oop.game.world.mementoStates;

import eg.edu.alexu.csd.oop.game.world.WorldImp;

public class MementoStateOff implements MementoState {
    private WorldImp.Memento myMemento;
    private int worldCounter;
    private int size;
    public MementoStateOff(WorldImp.Memento memento){
        myMemento=memento;
        worldCounter=0;
        size=myMemento.getShots().size();
    }
    @Override
    public void execute(Boolean timeout) {
        if(worldCounter< size-1){
            System.out.println("setting world");
            System.out.println("size = " +  size + " world counter = "+worldCounter);
            myMemento.setWorld(worldCounter);
            worldCounter++;
        }
    }
}
