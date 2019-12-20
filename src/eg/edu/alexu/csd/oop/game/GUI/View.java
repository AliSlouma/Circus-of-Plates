package eg.edu.alexu.csd.oop.game.GUI;

import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
     public LevelPanel levelPanel ;
     public MainGui mainGui;
     public PlayerPanel playerPanel;

    public View() {
        levelPanel = new LevelPanel();
        mainGui = new MainGui();
        playerPanel = new PlayerPanel();
        this.add(mainGui);
        this.setLayout(new CardLayout());
        this.setBounds(600, 200, 500, 700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Circus Of Lego");
        this.setVisible(true);
    }

}
