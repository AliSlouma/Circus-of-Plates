package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.Shapes;

public interface ShapesPool {

    void releaseShape(Shapes gameObject);

    Shapes getObject();
}
