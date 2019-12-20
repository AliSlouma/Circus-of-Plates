package eg.edu.alexu.csd.oop.game.world.Level;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LevelFactory {

    LevelState currentState;
    Map<String ,LevelState> state ;
    public LevelFactory() {
        state = new HashMap<>();
    }

    public void getObject(String level){
        if(!state.containsKey(level)){
            if(level.equals("easy"))
                state.put(level,new EasyLevel());
            else if(level.equals("medium"))
                state.put(level,new MediumLevel());
            else if(level.equals("hard"))
                state.put(level,new HardLevel());

        }
        this.currentState = state.get(level);
    }
    public void setState(String level){
        getObject(level);
    }

    public LevelState getState(){
        return this.currentState;
    }
}
