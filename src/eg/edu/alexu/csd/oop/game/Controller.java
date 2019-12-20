package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.GameEngine;
import eg.edu.alexu.csd.oop.game.object.PairOfPlayers;
import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.object.Players;
import eg.edu.alexu.csd.oop.game.world.GUI.View;
import eg.edu.alexu.csd.oop.game.world.Level.LevelFactory;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Controller {
   private View view;
   private LevelFactory level;
   String [] imagesName ;
   Players player;
    public Controller(View view , LevelFactory level){
        this.level = level;
        this.view = view;
        this.view.mainGui.Listener(new Listener());
        this.view.levelPanel.Listener(new Listener());
        this.view.playerPanel.Listener(new Listener());
        imagesName = new String[]{"batman.png" , "captain-america.png" ,"iron-man.png" , "joker.png" ,"spiderman.png"};

    }

    class Listener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getActionCommand().equals("Exit"))
            {
                view.setVisible(false);
                 view.dispose();
            }

           else if(e.getActionCommand().equals("Play"))
               closePanel(view.mainGui,view.playerPanel);
            else if(e.getActionCommand().equals("Single")){
                 player = new Player(500,550,"/characters/"+imagesName[(int)(Math.random() * imagesName.length)]);
                closePanel(view.playerPanel , view.levelPanel);
            }

            else if(e.getActionCommand().equals("Pair")){
                 player = new PairOfPlayers(500,550,"/characters/"+imagesName[(int)(Math.random() * imagesName.length)],"/characters/"+imagesName[(int)(Math.random() * imagesName.length)]);
                closePanel(view.playerPanel , view.levelPanel);
            }

           else {
                levelListener(e.getActionCommand());
                view.setVisible(false);
                view.dispose();
                runGame();
            }
        }
    }

    private void closePanel(JPanel old , JPanel newOne){
        view.add(newOne);
        view.remove(old);
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
        GameEngine.start("title", new WorldImp(this.player,level.getState()), Color.black);
    }




}
