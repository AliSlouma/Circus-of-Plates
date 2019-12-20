package eg.edu.alexu.csd.oop.game.object;

import eg.edu.alexu.csd.oop.game.GameObject;
import eg.edu.alexu.csd.oop.game.World;

import java.util.List;

public interface Players {

    public boolean intersect(GameObject gameObject, World world);
    public boolean putPiece(GameObject shape);

    /**
     * @return List of players contained
     */
    public List<Player> getPlayers();

    /**
     * @return player score
     */
    public int getScore();

    /**
     * @return return shapes to remove from player head
     */
    public List<GameObject> getRemovedShapes();
}
