package eg.edu.alexu.csd.oop.game.world.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class LevelPanel extends JPanel {

    JButton easy ;
    JButton medium ;
    JButton hard ;
    private BufferedImage image;
    String level;

    SplashScreen splash;
    public LevelPanel() {
        try {
            image = ImageIO.read(new File("lego.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image dimg = Objects.requireNonNull(image).getScaledInstance(500, 700, Image.SCALE_SMOOTH);
        ImageIcon imgg = new ImageIcon(dimg);
        JLabel picLabel = new JLabel(imgg);
        picLabel.setBounds(0,0,500,700);

        picLabel.add(easy);
        picLabel.add(medium);
        picLabel.add(hard);

        this.add(picLabel);

        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");

        easy.setBounds(150,150,200,60);
        medium.setBounds(150,260,200,60);
        hard.setBounds(150,370,200,60);
        easy.setBackground(Color.YELLOW);
        medium.setBackground(Color.YELLOW);
        hard.setBackground(Color.YELLOW);

        easy.addActionListener(e -> level = "easy");
        medium.addActionListener(e -> level = "medium");
        hard.addActionListener(e -> level = "hard");

        this.setBounds(0,0,500,700);
        this.setLayout(null);

    }

    public static void main (String []args){

        LevelPanel l = new LevelPanel();
        JFrame f =  new JFrame();
        f.setLayout(null);
        f.setBounds(0, 0, 500, 700);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Choose Level");
        f.add(l);
        f.setVisible(true);




    }


}
