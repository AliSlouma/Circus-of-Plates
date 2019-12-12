package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.Shapes;

import java.util.ArrayList;
import java.util.List;

public class ShapesPoolImp implements ShapesPool {

    private List<Shapes> pool;
    public static ShapesPool shapesPool = null;

    private ShapesPoolImp()
    {
        pool = new ArrayList<>();
        initialize();
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
            initialize();
        }

        return pool.remove(0);
    }

    private void initialize()
    {
        for (int i = 0; i < 20; i++)
        {

        }
    }
}
