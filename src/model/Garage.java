package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Garage {
    private List<Truck> trucks;

    public Garage(int capacity) {
        trucks = new CopyOnWriteArrayList<>();
        for (int i = 0; i < capacity; i++)
            trucks.add(new Truck((short)5));
    }


    public int getReadyIndex(){
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).isReady()) {
                System.out.println(i);
                return i;
            }
        }
        return -1;
    }

    public synchronized void fillGarage(IWooden as){
        while (getReadyIndex() == -1){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        trucks.get(getReadyIndex()).pushItem(as);
        System.out.println("Ready index is " + getReadyIndex());
        notify();
    }
}
