package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;

public interface Shapes {

    public boolean isused();
    public void use(boolean used);

    public int getScore();
    public GameObject getShape();
}
