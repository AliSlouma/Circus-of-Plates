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
        return ((GameObject)shape).getX();
    }

    @Override
    public void setX(int x) {
        ((GameObject)shape).setX(x);
    }

    @Override
    public int getY() {
        return ((GameObject)shape).getY();
    }

    @Override
    public void setY(int y) {
        ((GameObject)shape).setY(y);
    }

    @Override
    public int getWidth() {
        return ((GameObject)shape).getWidth();
    }

    @Override
    public int getHeight() {
        return ((GameObject)shape).getHeight();
    }

    @Override
    public boolean isVisible() {
        return ((GameObject)shape).isVisible();
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return new BufferedImage[0].;
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
}
