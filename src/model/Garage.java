package model;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Garage {
    private List<Truck> trucks;
    private int priorityTruck = 0;

    public Garage(int capacity) {
        trucks = new CopyOnWriteArrayList<>();
        for (int i = 0; i < capacity; i++)
            trucks.add(new Truck((short)5));
    }


    public int getReadyIndex(){
        // load already loaded Truck
        if (trucks.get(priorityTruck).isReady()) {
            return priorityTruck;
        }
        for (int i = 0; i < trucks.size(); i++) {
            if (trucks.get(i).isReady()) {
                priorityTruck = i;
                return i;
            }
        }
        return -1;
    }

    public synchronized void fillGarage(IWooden as){
        while (getReadyIndex() == -1){
            try {
                wait(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        (new Thread(() -> {trucks.get(getReadyIndex()).pushItem(as);})).start();
        System.out.println("Ready index is " + getReadyIndex());
        notify();
    }
}
