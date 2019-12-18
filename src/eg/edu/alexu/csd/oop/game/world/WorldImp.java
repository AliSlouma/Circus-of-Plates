package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.*;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.world.Level.LevelState;
import eg.edu.alexu.csd.oop.game.world.Level.OffLevel;
import eg.edu.alexu.csd.oop.game.world.mementoStates.MementoState;
import eg.edu.alexu.csd.oop.game.world.mementoStates.MementoStateOn;

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
    private int usedShapes;

    private boolean isDead;

    public WorldImp(Players player, LevelState level)
    {
        shapesPool = ShapesPoolImp.makeInstance();
        movableObjects = new ArrayList<>();
        controlObjects = new ArrayList<>();
        constantsObjects = new ArrayList<>();
        this.player = player;
        this.level = level;
        controlObjects.add((GameObject) player);
        usedShapes = 0;
    }

    private WorldImp(WorldImp cloned) {
        this.isDead = true;

        this.constantsObjects = new ArrayList<>();
        cloned.copyList(cloned.getConstantObjects(), this.constantsObjects);
        this.controlObjects = new ArrayList<>();
        cloned.copyList(cloned.getControlableObjects(), this.controlObjects);
        this.movableObjects = new ArrayList<>();
        cloned.copyList(cloned.getMovableObjects(), this.movableObjects);
    }

    private void copyList(List<GameObject> from, List<GameObject> to) {
        for (GameObject gameObject : from)
        {
            // Shape object
            if (gameObject instanceof Shape)
            {
                to.add(new Shape(gameObject.getX(), gameObject.getY(), ((Shape) gameObject).isused(), gameObject.getSpriteImages()[0]));
            }
            else if (gameObject instanceof Player)
            {
                to.add(new Player(gameObject.getX(), gameObject.getY(), gameObject.getSpriteImages()[0]));
            }
            else if (gameObject instanceof PairOfPlayers)
            {
                to.add(new PairOfPlayers(gameObject.getX(), gameObject.getY(), gameObject.getSpriteImages()[0]));
            }
        }
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
        if (!isDead)
        {
            boolean timeout = System.currentTimeMillis() - startTime > MAX_TIME;

            addMoreShapes();

            ListIterator<GameObject> iterable = movableObjects.listIterator();

            while (iterable.hasNext())
            {
                GameObject object = iterable.next();

                if (!((Shapes)object).isused() && intersect(object)) {
                    moveToController(object);
                }

                if (!player.putPiece(object)) {
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
        else
        {
            return false;
        }
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
        return 10;
    }

    private boolean intersect(GameObject gameObject) {
        return player.intersect(gameObject);
    }

    private void moveToController(GameObject object) {
        //object.setX((((GameObject)player).getX() + ((GameObject)player).getWidth() / 2) - object.getWidth() / 2);
        ((Shapes)object).use(true);
        usedShapes++;
        //controlObjects.add(object);
    }

    private void addMoreShapes() {
        if (movableObjects.size() - usedShapes < level.getMaxsize())
        {
            GameObject gameObject = (GameObject) shapesPool.getObject();
            gameObject.setY((int) ((-500 *Math.random()) - 10));
            gameObject.setX((int) Math.floor(Math.random() * (MAXWIDTH - 100)));
            movableObjects.add(gameObject);
        }
    }

    /**
     * Clone world object
     */
    private World cloneWorld() {
        return new WorldImp(this);
    }
    public class Memento{
        private ArrayList<World> shots;
        private MementoState state;
        public Memento(){
            shots=new ArrayList<>();
        }
        public void addWorld(World shot,Boolean timeout){
            if(state==null) state=new MementoStateOn(this);
            shots.add(shot);
            state.execute(timeout);
        }
        public void setState(MementoState state){
            this.state=state;
        }
        public ArrayList<World> getShots(){
            return shots;
        }
        public void setWorld(int index){
            World shot=shots.get(index);
            movableObjects=shot.getMovableObjects();
            constantsObjects=shot.getConstantObjects();
            controlObjects=shot.getControlableObjects();
            startTime = System.currentTimeMillis();
            level=new OffLevel();
        }
    }
}
