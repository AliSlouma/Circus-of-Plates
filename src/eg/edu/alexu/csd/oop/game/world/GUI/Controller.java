package eg.edu.alexu.csd.oop.game.world.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
   Model model;
   View view ;
   boolean flag = false;
    public Controller(View view ) {
        this.view = view;
        this.view.mainGui.Listener(new CalculateListener());
        this.view.levelPanel.Listener(new CalculateListener());

    }

    class CalculateListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           if(!flag){
               mainListener();
                flag = true;

           }
           else {
               view.levelPanel.flag=true;
               levelListener(e.getActionCommand());
           }
        }
    }
    private void mainListener(){
        System.out.println("ali");
        view.add(view.levelPanel);
        view.remove(view.mainGui);
        view.getContentPane().invalidate();
        view.getContentPane().validate();
    }
    private void levelListener(String s){

        if(s.toLowerCase().equals("easy"))
           System.out.println("");
        else if(s.toLowerCase().equals("medium"))
            System.out.println("");
        else if(s.toLowerCase().equals("hard"))
            System.out.println("");


    }





}
