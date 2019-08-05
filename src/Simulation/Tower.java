package Simulation;

import java.util.ArrayList;
import java.util.List;

public class Tower {
    private static List<iFlyable> observers = new ArrayList<iFlyable>();

    public void register(iFlyable flyable){
        if (!observers.contains(flyable))
        observers.add(flyable);
    }
    public void unregister(iFlyable flyable){
        observers.remove(flyable);
    }

    protected void conditionsChanged(){
        for (int i = 0; i < observers.size(); i++){
            observers.get(i).updateConditions();
        }
    }
}
