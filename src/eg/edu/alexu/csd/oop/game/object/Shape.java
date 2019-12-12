package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

import java.awt.image.BufferedImage;

public class Shape implements GameObject, Shapes {
    private static final int SPRITES_NUMBER = 1;

    private BufferedImage[] spriteImages = new BufferedImage[SPRITES_NUMBER];
    private int x;
    private int y;
    private boolean visible;

    public Shape(int posX, int posY){
        this.x = posX;
        this.y = posY;
        this.visible = true;

        // Create a buffered image
        spriteImages[0] = ImageFactoryImplementation.getInstance().createImage();
    }

    @Override
    public int getX() {
        return this.x;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public BufferedImage[] getSpriteImages() {
        return this.spriteImages;
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
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
