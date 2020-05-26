package vue;

import java.util.ArrayList;

public abstract class Observable {
    private final ArrayList<Observer> observers;
    public Observable() {
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void checkEnd(Boolean dead){
        if(dead) {
            for (Observer o : observers) {
                System.out.println("END");
                o.end();
            }
        }
    }

    public void notifyObservers() {
        for(Observer o : observers) {
            o.update();
        }
    }
}
