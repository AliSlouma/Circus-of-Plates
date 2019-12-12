package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.Players;
import eg.edu.alexu.csd.oop.game.object.Shapes;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.world.Level.LevelState;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class WorldImp implements World {

    private static int MAX_TIME = 60 * 1000;
    private long startTime = System.currentTimeMillis();
    private List<GameObject> movableObjects;
    private List<GameObject> constantsObjects;
    private List<GameObject> controlObjects;
    private LevelState level;
    private final int MAXWIDTH = 1000;
    private final int MAXHIGHT = 1000;
    private ShapesPool shapesPool;
    private Players player;
    WorldImp(Players player, LevelState level)
    {
        shapesPool = ShapesPoolImp.makeInstance();
        movableObjects = new ArrayList<>();
        controlObjects = new ArrayList<>();
        constantsObjects = new ArrayList<>();
        this.player = player;
        this.level = level;
        controlObjects.add((GameObject) player);
    }

    @Override
    public List<GameObject> getConstantObjects() {
        return constantsObjects;
    }

    @Override
    public List<GameObject> getMovableObjects() {
        return movableObjects;
    }

    @Override
    public List<GameObject> getControlableObjects() {
        return controlObjects;
    }

    @Override
    public int getWidth() {
        return MAXWIDTH;
    }

    @Override
    public int getHeight() {
        return MAXHIGHT;
    }

    @Override
    public boolean refresh() {
        boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;

        addMoreShapes();

        ListIterator<GameObject> iterable = movableObjects.listIterator();

        while (iterable.hasNext())
        {
            GameObject object = iterable.next();

            if (intersect(object))
            {
                moveToController(object);
                iterable.remove();
            }
            else
            {
                object.setY(getSpeed() + object.getY());
                if (object.getY() > MAXHIGHT) {
                    shapesPool.releaseShape((Shapes) object);
                    iterable.remove();
                }
            }
        }
        addMoreShapes();
        return !timeout;
    }

    @Override
    public String getStatus() {
        return null;
    }

    @Override
    public int getSpeed() {
        return level.getVelocity();
    }

    @Override
    public int getControlSpeed() {
        return level.getVelocity();
    }

    private boolean intersect(GameObject gameObject) {
        return player.intersect(gameObject);
    }

    private void moveToController(GameObject object) {
        controlObjects.add(object);
    }

    private void addMoreShapes() {
        if (movableObjects.size() < level.getMaxsize())
        {
            GameObject gameObject = (GameObject) shapesPool.getObject();
            gameObject.setY((int) (-90 * (Math.random()) - 10));
            gameObject.setX((int) Math.floor(Math.random() * (MAXWIDTH - 100)));
            movableObjects.add(gameObject);
        }
    }
}
