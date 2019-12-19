package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Shapes {

    public boolean isused();
    public void use(boolean used);

    /**
     * @return if three shapes have similar color
     */
    public boolean isSimilar(GameObject shape1, GameObject shape2, GameObject shape3);

    public int getScore();
}
