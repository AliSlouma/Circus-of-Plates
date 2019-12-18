package eg.edu.alexu.csd.oop.game.world.mementoStates;

import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import java.util.ArrayList;

public class MementoStateOff implements MementoState {
    private WorldImp.Memento myMemento;
    private static int unitTime=1500;
    public MementoStateOff(WorldImp.Memento memento){
        myMemento=memento;
    }
    @Override
    public void execute(Boolean timeout) {
        int siz=myMemento.getShots().size();
        long startTime = System.currentTimeMillis();
        //wait unit time before start changing world
        while (System.currentTimeMillis()-startTime  <   unitTime){ }
        for(int i=0;i<siz-1;i++){
            myMemento.setWorld(i);
            startTime = System.currentTimeMillis();
            while (System.currentTimeMillis()-startTime  <  unitTime){ }
        }

    }
}
