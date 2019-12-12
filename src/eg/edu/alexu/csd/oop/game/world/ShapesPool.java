package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface ShapesPool {

    void releaseShape(GameObject gameObject);

    GameObject getObject();
}
