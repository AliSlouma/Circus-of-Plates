package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScoreLabel implements GameObject {
    private BufferedImage[] spriteImages = new BufferedImage[1];
    private int score;

    public ScoreLabel() {
        spriteImages[0] = new BufferedImage(150, 100, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public int getX() {
        return 450;
    }

    @Override
    public void setX(int x) {
    }

    @Override
    public int getY() {
        return 300;
    }

    @Override
    public void setY(int y) {
    }

    @Override
    public int getWidth() {
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return this.score;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        Graphics graphics = spriteImages[0].getGraphics();
        graphics.drawString("Score: " + this.score, 0, 0);

        return spriteImages;
    }
}
