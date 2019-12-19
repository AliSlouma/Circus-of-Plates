package eg.edu.alexu.csd.oop.game.object.Decorators;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shapes;
import eg.edu.alexu.csd.oop.game.utility.HelperClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EvilDecorator implements Shapes, GameObject {

    private static final int SPRITES_NUMBER = 1;
    private Shapes shape;
    private BufferedImage[] spriteImages = new BufferedImage[SPRITES_NUMBER];


    public EvilDecorator(Shapes gameObject)
    {
        this.shape = gameObject;

        String[] imagePaths = {"/wrappers/black-wrapper-rectangle.png", "/wrappers/black-wrapper-square.png"};
        for (String imagePath : imagePaths)
        {
            BufferedImage loadedImage;

            try {
                loadedImage = ImageIO.read(this.getClass().getResourceAsStream(imagePath));

                if (HelperClass.matchingImages(loadedImage, getShape().getSpriteImages()[0]))
                {
                    this.spriteImages[0] = HelperClass.overlayImages(getShape().getSpriteImages()[0], loadedImage);
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getX() {
        return this.getShape().getX();
    }

    @Override
    public void setX(int x) {
        this.getShape().setX(x);
    }

    @Override
    public int getY() {
        return this.getShape().getY();
    }

    @Override
    public void setY(int y) {
        this.getShape().setY(y);
    }

    @Override
    public int getWidth() {
        return this.getShape().getWidth();
    }

    @Override
    public int getHeight() {
        return this.getShape().getHeight();
    }

    @Override
    public boolean isVisible() {
        return this.getShape().isVisible();
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.spriteImages;
    }

    @Override
    public boolean isused() {
        return this.shape.isused();
    }

    @Override
    public void use(boolean used) {
        this.shape.use(used);
    }

    @Override
    public boolean isSimilar(GameObject shape1, GameObject shape2, GameObject shape3) {
        return this.shape.isSimilar(shape1, shape2, shape3);
    }

    @Override
    public int getScore() {
        return this.shape.getScore() - 50;
    }

    @Override
    public GameObject getShape() {
        return (GameObject) this.shape;
    }
}
