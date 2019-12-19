package eg.edu.alexu.csd.oop.game.object.Decorators;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Shape;
import eg.edu.alexu.csd.oop.game.object.Shapes;

import java.awt.image.BufferedImage;

public class EvilDecorator implements Shapes, GameObject {

    private Shapes shape;

    public EvilDecorator(Shapes gameObject)
    {
        shape = gameObject;
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
        return new BufferedImage[0];
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
        return 0;
    }

    @Override
    public GameObject getShape() {
        return (GameObject) shape;
    }
}
