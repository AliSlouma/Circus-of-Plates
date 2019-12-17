package eg.edu.alexu.csd.oop.game.world.GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class MainGui extends JPanel {
    private BufferedImage image;

    ArrayList<JButton> buttons = new ArrayList<>();
    public MainGui() {
        try {
            image = ImageIO.read(new File("wa.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = Objects.requireNonNull(image).getScaledInstance(500, 700, Image.SCALE_SMOOTH);
        ImageIcon img = new ImageIcon(dimg);
        JLabel picLabel = new JLabel(img);
        picLabel.setBounds(0,0,500,700);

        this.add(picLabel);
        this.setBounds(0,0,500,700);
        this.setLayout(null);

        String[] levelName = {"Play","Exit"};
        for(int i=0;i<2;i++){
            JButton button = new JButton(levelName[i]);
            buttons.add(button);
            button.setBounds(150,150+(i*110),200,60);
            button.setBackground(Color.red);
            picLabel.add(button);
             int f =i;
            button.addActionListener(e -> System.out.println(levelName[f]));
           // button.setContentAreaFilled(false);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setForeground(Color.black);
         //   button.setBorder(BorderFactory.createBevelBorder(1)); // Inner Bevel Border
        }

    }
    void Listener(ActionListener listenForButton){
        for(JButton button : buttons)
            button.addActionListener(listenForButton);

    }

}

