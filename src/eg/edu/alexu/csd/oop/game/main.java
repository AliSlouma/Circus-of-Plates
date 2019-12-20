package eg.edu.alexu.csd.oop.game;

import eg.edu.alexu.csd.oop.game.object.Player;
import eg.edu.alexu.csd.oop.game.world.Level.EasyLevel;
import eg.edu.alexu.csd.oop.game.world.WorldImp;

import javax.swing.*;
import java.awt.*;

public class main {
    public static void main(String[]args)
    {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem pauseMenuItem = new JMenuItem("Pause");
        JMenuItem resumeMenuItem = new JMenuItem("Resume");
        menu.add(newMenuItem);
        menu.addSeparator();
        menu.add(pauseMenuItem);
        menu.add(resumeMenuItem);
        menuBar.add(menu);
        //final GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new eg.edu.alexu.csd.oop.game.sample.world.Space(400, 700), menuBar, Color.BLACK);
        final GameEngine.GameController gameController = GameEngine.start("Very Simple Game in 99 Line of Code", new WorldImp(new Player(0, 0 , "/batman.png"),new EasyLevel()), menuBar, Color.BLACK);
        /*newMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController.changeWorld(new eg.edu.alexu.csd.oop.game.world.Space(400, 700));
            }
        });
        pauseMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController.pause();
            }
        });
        resumeMenuItem.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameController.resume();
            }
        });*/
    }
}

