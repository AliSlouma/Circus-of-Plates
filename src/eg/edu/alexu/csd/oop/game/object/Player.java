package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.utility.HelperClass;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player implements GameObject, Players {
    private static final int MAX_MSTATE = 1;

    // an array of sprite images that are drawn sequentially
    private BufferedImage[] spriteImages = new BufferedImage[MAX_MSTATE];
    private int x;
    private int y;
    private boolean visible;
    private int top;

    private List<Shapes> shapes = new ArrayList<>();
    private List<Shapes> allShapes = new ArrayList<>();
    private List<GameObject> removedShapes = new ArrayList<>();
    private int score;

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

    public Player(Player player) {
        this.x = player.getX();
        this.y = player.getY();
        this.visible = true;
        this.top = this.y;

        this.spriteImages[0] = player.getSpriteImages()[0];
    }

    @Override
    public boolean intersect(GameObject gameObject, World world) {
        if (!((Shapes)gameObject).isused() && Math.abs((gameObject.getX() + gameObject.getWidth() / 2) - (this.getX() + this.getWidth() / 2))  <=  (0.5 * (gameObject.getWidth() + this.getWidth())))
        {
            if (Math.abs(gameObject.getHeight() + gameObject.getY() - top) <= 1)
            {
                top -= gameObject.getHeight();

                if (top < 0)
                {
                    this.score -= 5;
                }

                this.addToShapes((Shapes) gameObject);
                if (HelperClass.areSimilarShapes(this.shapes))
                {
                    for (Shapes shape : this.shapes)
                    {
                        this.score += shape.getScore();
                        this.removedShapes.add((GameObject) shape);
                        this.top += shape.getShape().getHeight();
                    }

                    // Remove all shapes
                    this.allShapes.removeAll(this.shapes);
                    this.shapes.clear();

                    for (int i = 0; i < 3; i++)
                    {
                        if (this.allShapes.size() <= 0)
                        {
                            break;
                        }

                        this.shapes.add(0, this.allShapes.remove(this.allShapes.size() - 1));
                    }
                }

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
        if (((Shapes)shape).isused() && this.isSameX(shape)) {
            shape.setX((this.getX() + this.getWidth() / 2) - shape.getWidth() / 2);
            ((Shapes)shape).use(true);
            return true;
        }
        else return false;
    }

    @Override
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(this);

        return players;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public List<GameObject> getRemovedShapes() {
        return this.removedShapes;
    }

    /**
     * @return true if shape and player intersect in x
     */
    public boolean isSameX(GameObject gameObject) {
        return Math.abs((gameObject.getX() + gameObject.getWidth() / 2) - (this.getX() + this.getWidth() / 2)) <= (0.5 * (gameObject.getWidth() + this.getWidth()));
    }

    public void addToShapes(Shapes shape) {
        this.allShapes.add(shape);
        this.shapes.add(shape);

        // Only last three shapes
        while (this.shapes.size() > 3)
        {
            this.shapes.remove(0);
        }
    }

    public List<Shapes> getShapes() {
        return this.shapes;
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
