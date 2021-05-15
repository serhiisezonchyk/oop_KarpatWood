package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Garage {
    private List<Truck> trucks;
    private int priorityTruck = 0;
    JLabel lbl;
    mainGui mg;

    public Garage(JLabel lblCar1,JLabel lblCar2, mainGui mg,int size1, int size2) {
        this.mg = mg;
        trucks = new CopyOnWriteArrayList<>();
        trucks.add(new Truck((short)size1, lblCar1, mg, mg.getSpinnerCar1()));
        trucks.add(new Truck((short)size2, lblCar2, mg, mg.getSpinnerCar2()));
    }


    public  int getReadyIndex(){
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
        //Проверка перед отправкой так сказатб
        //new Thread (()->{trucks.get(getReadyIndex()).checkMax();}).start();
        
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        (new Thread(() -> {
        	trucks.get(getReadyIndex()).pushItem(as);
        	})).start();
        System.out.println("Ready index is " + getReadyIndex());
        notify();
    }
}
