package eg.edu.alexu.csd.oop.game.object.Decorators;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shape;
import eg.edu.alexu.csd.oop.game.object.Shapes;
import eg.edu.alexu.csd.oop.game.utility.HelperClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BonusDecorator implements Shapes, GameObject {

    private Shapes shape;
    private String[] BonusWrappers;
    private static final int SPRITES_NUMBER = 1;
    private BufferedImage[] spriteImages = new BufferedImage[SPRITES_NUMBER];

    public BonusDecorator(Shapes gameObject) {
        shape = gameObject;
        BonusWrappers = new String[2];
        BonusWrappers[0] = "/wrappers/gold-wrapper-rectangle.png";
        BonusWrappers[1] = "/wrappers/gold-wrapper-square.png";
        try {
            setappropirateImage();
        } catch (IOException e) {
            System.out.println("Cant load the wrapped Image");

        }
    }

    @Override
    public int getX() {
        return getShape().getX();
    }

    @Override
    public void setX(int x) {
        getShape().setX(x);
    }

    @Override
    public int getY() {
        return getShape().getY();

    }

    @Override
    public void setY(int y) {
        getShape().setY(y);
    }

    @Override
    public int getWidth() {
        return getShape().getWidth();
    }

    @Override
    public int getHeight() {
        return getShape().getHeight();
    }

    @Override
    public boolean isVisible() {
        return getShape().isVisible();
    }

    @Override
    public BufferedImage[] getSpriteImages() {

        return spriteImages;
    }

    @Override
    public boolean isused() {
        return shape.isused();
    }

    @Override
    public void use(boolean used) {
        shape.use(used);
    }

    @Override
    public boolean isSimilar(GameObject shape1, GameObject shape2, GameObject shape3) {
        return shape.isSimilar(shape1, shape2, shape3);
    }

    @Override
    public int getScore() {
        return 50 + shape.getScore();
    }

    @Override
    public GameObject getShape() {
        return (GameObject) shape;
    }

    private void setappropirateImage() throws IOException {
        for (String pic : BonusWrappers)
        {
            BufferedImage image = ImageIO.read(this.getClass().getResourceAsStream(pic));
            if (HelperClass.matchingImages(image, getShape().getSpriteImages()[0]))
            {
               spriteImages[0] = HelperClass.overlayImages(getShape().getSpriteImages()[0], image);
               break;
            }
        }
    }
}
