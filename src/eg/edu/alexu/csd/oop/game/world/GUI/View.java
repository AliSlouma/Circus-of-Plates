package eg.edu.alexu.csd.oop.game.world.GUI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public LevelPanel levelPanel ;
    public MainGui mainGui;
    public View() {
        levelPanel = new LevelPanel();
        mainGui = new MainGui();

        this.add(mainGui);
        this.setLayout(new CardLayout());
        this.setBounds(0, 0, 500, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Choose Level");
        this.setVisible(true);
    }

    public static void  main(String[]args){
        View v = new View();
        Controller c=  new Controller(v);


    }
}
