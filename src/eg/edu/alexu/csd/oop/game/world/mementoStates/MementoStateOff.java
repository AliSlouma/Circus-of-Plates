package eg.edu.alexu.csd.oop.game.world.mementoStates;

import eg.edu.alexu.csd.oop.game.GUI.SimpleAudioPlayer;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class MementoStateOff implements MementoState {
    private WorldImp.Memento myMemento;
    private int worldCounter;
    private int size;
    private SimpleAudioPlayer audioPlayer;

    {
        try {
            audioPlayer = new SimpleAudioPlayer();
            audioPlayer.startaudio();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public MementoStateOff(WorldImp.Memento memento){
        myMemento=memento;
        size=myMemento.getShots().size();
        worldCounter=size-2;
    }
    @Override
    public void execute(Boolean timeout) {
        if(worldCounter>= 0){
            WorldImp.myLogger.config("setting world "+"size = " +  size + " world counter = "+worldCounter);
           // System.out.println(("setting world "+"size = " +  size + " world counter = "+worldCounter));
            myMemento.setWorld(worldCounter);
            worldCounter--;
        }
    }
}
