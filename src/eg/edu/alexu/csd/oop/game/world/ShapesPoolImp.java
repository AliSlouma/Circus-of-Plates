package eg.edu.alexu.csd.oop.game.world;

import eg.edu.alexu.csd.oop.game.object.Decorators.BonusDecorator;
import eg.edu.alexu.csd.oop.game.object.Decorators.EvilDecorator;
import eg.edu.alexu.csd.oop.game.object.Shapes;
import eg.edu.alexu.csd.oop.game.object.Shape;

import java.util.ArrayList;
import java.util.List;

public class ShapesPoolImp implements ShapesPool {

    private static final int MAXWIDTH = 1000;
    private List<Shapes> pool;
    private static ShapesPool shapesPool = null;
    private final double bonusProb = 0.1;
    private final double evilProb = 0.2;
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
    public void releaseShape(Shapes gameObject)
    {
        pool.add((Shapes) gameObject.getShape());
    }

    @Override
    public Shapes getObject() {
        if (pool.size() == 0)
        {
            initialize(5);
        }
        return calculateProb();
    }

    private void initialize(int size)
    {
        for (int i = 0; i < size; i++)
        {
            pool.add(new Shape((int) Math.floor(Math.random() * (MAXWIDTH - 100)), (int) ((-500 * Math.random()) - 10)));
        }
    }

    private Shapes calculateProb()
    {
        double randomProb = Math.random();
        Shapes shape = pool.remove(0);

        if (randomProb <= this.bonusProb) {
            return new BonusDecorator(shape);
        }
        else if (randomProb <= this.evilProb) {
            return new EvilDecorator(shape);
        }
        return shape;
    }
}
