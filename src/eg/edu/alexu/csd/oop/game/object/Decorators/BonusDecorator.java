package eg.edu.alexu.csd.oop.game.object.Decorators;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shape;
import eg.edu.alexu.csd.oop.game.object.Shapes;

import java.awt.image.BufferedImage;

public class BonusDecorator implements Shapes, GameObject {

    private Shapes shape;

    BonusDecorator(Shape gameObject)
    {
        shape = gameObject;
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
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[0];
    }

    @Override
    public boolean isused() {
        return false;
    }

    @Override
    public void use(boolean used) {

    }

    @Override
    public boolean isSimilar(GameObject shape1, GameObject shape2, GameObject shape3) {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }
}
