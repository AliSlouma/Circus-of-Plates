package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player implements GameObject, Players {
    private static final int MAX_MSTATE = 1;
    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int top;
    public Player(int posX, int posY, String ImagePath){
        this.x = posX;
        this.y = posY;
        this.visible = true;
        // create a bunch of buffered images and place into an array, to be displayed sequentially
        try {
            spriteImages[0] = ImageIO.read(getClass().getResourceAsStream(ImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        top = y;
    }

    @Override
    public boolean intersect(GameObject gameObject) {
        if (Math.abs((gameObject.getX() + gameObject.getWidth() / 2) - (this.getX() + this.getWidth() / 2))  <=  (0.5 * (gameObject.getWidth() + this.getWidth())))
        {
            if (Math.abs(gameObject.getHeight() + gameObject.getY() - top) <= 1)
            {
                top -= gameObject.getHeight();
                return true;
            }
            else
                return false;
        }
        else
            return false;
    }

    @Override
    public boolean putPiece(GameObject shape) {
        if (((Shape)shape).isused() && this.isSameX(shape)) {
            shape.setX((this.getX() + this.getWidth() / 2) - shape.getWidth() / 2);
            ((Shape)shape).use(true);
            return true;
        }
        else return false;
    }

    /**
     * @return true if shape and player intersect in x
     */
    public boolean isSameX(GameObject gameObject) {
        return Math.abs((gameObject.getX() + gameObject.getWidth() / 2) - (this.getX() + this.getWidth() / 2)) <= (0.5 * (gameObject.getWidth() + this.getWidth()));
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public void setX(int mX) {
        this.x = mX;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setY(int mY)
    {
       // this.y = mY;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return spriteImages;
    }

    @Override
    public int getWidth(){
        return spriteImages[0].getWidth();
    }

    @Override
    public int getHeight() {
        return spriteImages[0].getHeight();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible){
        this.visible = visible;
    }

}
