package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.object.PairOfPlayers;
import eg.edu.alexu.csd.oop.game.world.GUI.View;
import eg.edu.alexu.csd.oop.game.world.Level.LevelFactory;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Controller {
   private View view;
   private LevelFactory level;
    public Controller(View view , LevelFactory level){
        this.level = level;
        this.view = view;
        this.view.mainGui.Listener(new Listener());
        this.view.levelPanel.Listener(new Listener());
    }

    class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Exit"))
            {
                view.setVisible(false);
                 view.dispose();
            }

           else if(e.getActionCommand().equals("Play"))
               mainListener();

           else {
                levelListener(e.getActionCommand());
                System.out.println(level.getState().getClass());
                view.setVisible(false);
                view.dispose();
                runGame();
            }
        }
    }
    // this is for closing the main panel and open the level panel
    private void mainListener(){
        view.add(view.levelPanel);
        view.remove(view.mainGui);
        view.getContentPane().invalidate();
        view.getContentPane().validate();
    }
    private void levelListener(String s){
        if(s.toLowerCase().equals("easy"))
           level.setState("easy");
        else if(s.toLowerCase().equals("medium"))
            level.setState("medium");
        else if(s.toLowerCase().equals("hard"))
            level.setState("hard");
    }
    private void runGame(){
        GameEngine.start("title", new WorldImp(new PairOfPlayers(500, 550, "/characters/batman.png", "/characters/joker.png"),level.getState()), Color.black);
    }



}
