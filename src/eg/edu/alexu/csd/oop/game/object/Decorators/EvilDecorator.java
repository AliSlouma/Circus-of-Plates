package eg.edu.alexu.csd.oop.game.object.Decorators;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shape;
import eg.edu.alexu.csd.oop.game.object.Shapes;
import eg.edu.alexu.csd.oop.game.utility.HelperClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class EvilDecorator implements Shapes, GameObject {

    private Shapes shape;
    private BufferedImage image;


    EvilDecorator(Shape gameObject)
    {
        this.shape = gameObject;

        String[] imagePaths = {"wrappers/black-wrapper-rectangle.png", "wrappers/black-wrapper-square.png"};
        for (String imagePath : imagePaths)
        {
            BufferedImage loadedImage;

            try {
                loadedImage = ImageIO.read(getClass().getResourceAsStream(imagePath));

                if (HelperClass.matchingImages(loadedImage, gameObject.getSpriteImages()[0]))
                {
                    this.image = HelperClass.overlayImages(loadedImage, gameObject.getSpriteImages()[0]);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    @Override
    public int getX() {
        return ((GameObject) this.shape).getX();
    }

    @Override
    public void setX(int x) {
        ((GameObject) this.shape).setX(x);
    }

    @Override
    public int getY() {
        return ((GameObject) this.shape).getY();
    }

    @Override
    public void setY(int y) {
        ((GameObject) this.shape).setY(y);
    }

    @Override
    public int getWidth() {
        return ((GameObject) this.shape).getWidth();
    }

    @Override
    public int getHeight() {
        return ((GameObject) this.shape).getWidth();
    }

    @Override
    public boolean isVisible() {
        return ((GameObject) this.shape).isVisible();
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[]{this.image};
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
