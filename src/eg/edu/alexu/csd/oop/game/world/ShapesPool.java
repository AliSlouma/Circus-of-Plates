package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.object.Shapes;

public interface ShapesPool {

    void releaseShape(Shapes gameObject);

    Shapes getObject();
}
