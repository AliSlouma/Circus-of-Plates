package eg.edu.alexu.csd.oop.game.object.constants;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Replay implements GameObject {
    private BufferedImage[] spriteImages = new BufferedImage[1];

    public Replay() {
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream("/replay-image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public void setX(int x) {
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setY(int y) {
    }

    @Override
    public int getWidth() {
        return this.spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return this.spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return true;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.spriteImages;
    }
}
