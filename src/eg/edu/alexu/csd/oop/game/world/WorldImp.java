package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.object.*;
import eg.edu.alexu.csd.oop.game.World;
import eg.edu.alexu.csd.oop.game.object.Decorators.BonusDecorator;
import eg.edu.alexu.csd.oop.game.object.Decorators.EvilDecorator;
import eg.edu.alexu.csd.oop.game.world.Level.LevelState;
import eg.edu.alexu.csd.oop.game.world.Level.OffLevel;
import eg.edu.alexu.csd.oop.game.world.mementoStates.MementoState;
import eg.edu.alexu.csd.oop.game.world.mementoStates.MementoStateOn;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WorldImp implements World {
    private final int sendMementoRate = 10;
    private final int replayRate = 4;
    private final int MAXWIDTH = 1000;
    private final int MAXHIGHT = 800;
    private static int MAX_TIME = 30 * 1000;
    private long startTime = System.currentTimeMillis();
    private List<GameObject> movableObjects;
    private List<GameObject> constantsObjects;
    private List<GameObject> controlObjects;
    private LevelState level;
    private ShapesPool shapesPool;
    private Players player;
    private int usedShapes;
    private boolean isDead;
    private int time;
    private Memento memento;
    private long MementoTime;
    public static Logger myLogger;
    private int logTime;
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
        time = 5;
        memento = new Memento();
        System.setProperty("java.util.logging.config.file", "logging.properties");
        myLogger=Logger.getLogger("worldlogger");
        myLogger.setLevel(Level.INFO);
        logTime=0;
    }

    private WorldImp(WorldImp cloned) {
        this.isDead = true;

        this.constantsObjects = new ArrayList<>();
        this.controlObjects = new ArrayList<>();
        this.movableObjects = new ArrayList<>();

        cloned.copyList(cloned.getConstantObjects(), this.constantsObjects);
        cloned.copyList(cloned.getControlableObjects(), this.controlObjects);
        cloned.copyList(cloned.getMovableObjects(), this.movableObjects);
    }

    private void copyList(List<GameObject> from, List<GameObject> to) {
        for (GameObject gameObject : from)
        {
            // Shape object
            if (gameObject instanceof Shape)
            {
                to.add(new Shape((Shape) gameObject));
            }
            // Player object
            else if (gameObject instanceof Player)
            {
                to.add(new Player((Player) gameObject));
            }
            // Pair object
            else if (gameObject instanceof PairOfPlayers)
            {
                to.add(new PairOfPlayers((PairOfPlayers) gameObject));
            }
            // Evil decorator
            else if (gameObject instanceof EvilDecorator)
            {
                to.add(new EvilDecorator((EvilDecorator) gameObject));
            }
            else if (gameObject instanceof BonusDecorator)
            {
                to.add(new BonusDecorator((BonusDecorator) gameObject));
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

    private void sendMomento (boolean timeOut, double currentTime)
    {
        if (currentTime > time)
        {
            myLogger.config("new frame stored ");
            time += sendMementoRate;
            memento.addWorld(this.cloneWorld(), timeOut);
        }
    }

    @Override
    public boolean refresh() {
        double currentTime = System.currentTimeMillis() - startTime;

        boolean timeout = currentTime > MAX_TIME;
        if (!isDead)
        {
            if(currentTime>logTime){
                myLogger.info("controller at position X = "+getControlableObjects().get(0).getX()+" .");
                logTime+=1000;
            }
            sendMomento(timeout, currentTime);
            addMoreShapes();

            ListIterator<GameObject> iterable = movableObjects.listIterator();

            while (iterable.hasNext())
            {
                GameObject object = iterable.next();

                if (!((Shapes)object).isused() && intersect(object)) {
                    myLogger.info(((Shapes)object).toString()+" intersected and added to Controller objects");
                    moveToController(object);
                }

                if (!player.putPiece(object)) {
                    object.setY(getSpeed() + object.getY());
                    object.setX((int) ((level.getX_Coordinate() * Math.random() * 2)  - level.getX_Coordinate() + object.getX()));
                    if (object.getY() > MAXHIGHT) {
                        myLogger.info(((Shapes) object).toString()+" released and is available in the pool.");
                        shapesPool.releaseShape((Shapes) object);
                        iterable.remove();
                    }
                }
            }
            addMoreShapes();
            MementoTime = System.currentTimeMillis();
            return !timeout;
        }
        else if(System.currentTimeMillis()-MementoTime >  replayRate)
        {
            memento.addWorld(null,timeout);
            MementoTime=System.currentTimeMillis();

            return true;
        }else{

            return false;
        }
    }

    @Override
    public String getStatus() {
        return "Score=" + 0 + "   |   Time=" + Math.max(0, (MAX_TIME - (System.currentTimeMillis()-startTime))/1000);
    }

    @Override
    public int getSpeed() {
        return level.getVelocity();
    }

    @Override
    public int getControlSpeed() {
        return level.getControllerVelocity();
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
            myLogger.info(gameObject.toString()+" is created and added to movable object");
            movableObjects.add(gameObject);
        }
    }

    /**
     * Clone world object
     */
    private WorldImp cloneWorld() {
        return new WorldImp(this);
    }

    public class Memento{
        private ArrayList<World> shots;
        private MementoState state;
        Memento(){
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
            level=new OffLevel();
            isDead=true;
        }
    }
}
