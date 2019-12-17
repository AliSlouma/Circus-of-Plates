package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * A pair of sprites tied together
 */
public class PairOfPlayers implements GameObject, Players {
    private static final int SPRITES_NUMBER = 2;

    private Player[] players = new Player[2];  // Player pair

    private BufferedImage[] spriteImages = new BufferedImage[1];
    private int x;
    private int y;
    private boolean visible;

    public PairOfPlayers(int posX, int posY, String img1Path, String img2Path) {
        this.x = posX;
        this.y = posY;
        this.visible = true;

        players[0] = new Player(posX, posY, img1Path);
        players[1] = new Player(posX + players[0].getWidth(), posY, img2Path);

        // Merge sprite images
        try {
            BufferedImage image1 = ImageIO.read(getClass().getResourceAsStream(img1Path));
            BufferedImage image2 = ImageIO.read(getClass().getResourceAsStream(img2Path));

            int width = image1.getWidth() + image2.getWidth();
            int height = Math.max(image1.getHeight(), image2.getHeight());
            spriteImages[0] = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics graphics = spriteImages[0].getGraphics();
            graphics.drawImage(image1, 0, 0, null);
            graphics.drawImage(image2, image1.getWidth(), 0, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean intersect(GameObject gameObject) {
        return players[0].intersect(gameObject) || players[1].intersect(gameObject);
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;

        players[0].setX(x);
        players[1].setX(x + players[0].getWidth());
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;

        players[0].setY(y);
        players[1].setY(y);
    }

    @Override
    public int getWidth() {
        return players[0].getWidth() + players[1].getWidth();
    }

    @Override
    public int getHeight() {
        return Math.max(players[0].getHeight(), players[1].getHeight());
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;

        players[0].setVisible(visible);
        players[1].setVisible(visible);
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.spriteImages;
    }
}
