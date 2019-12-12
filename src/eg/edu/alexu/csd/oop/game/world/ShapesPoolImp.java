package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.Shapes;
import eg.edu.alexu.csd.oop.game.object.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapesPoolImp implements ShapesPool {

    private List<Shapes> pool;
    public static ShapesPool shapesPool = null;

    private ShapesPoolImp()
    {
        pool = new ArrayList<>();
        initialize(20);
    }

    public static ShapesPool makeInstance()
    {
        if (shapesPool == null)
            shapesPool = new ShapesPoolImp();

        return shapesPool;
    }

    @Override
    public void releaseShape(Shapes gameObject) {
        pool.add(gameObject);
    }

    @Override
    public Shapes getObject() {
        if (pool.size() == 0)
        {
            initialize(5);
        }
        return pool.remove(0);
    }

    private void initialize(int SIZE)
    {
        for (int i = 0; i < SIZE; i++)
        {
            pool.add(new Shape());
        }
    }
}
