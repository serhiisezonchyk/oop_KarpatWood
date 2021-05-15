package model;

import GUI.mainGui;

import javax.swing.*;

public class Truck extends AbstractCollection{

    private boolean isReady = true;
    	JSpinner spinner; 
    public boolean isReady() {
        return isReady;
    }

    public Truck(short capacity, JLabel lbl, mainGui mg, JSpinner spinner) {
        super(capacity, lbl, mg,null);
        this.spinner = spinner;
    }

    private  void unLoad(){
    	mg.setCarActivity(lbl, 2);
        System.out.println("Truck has gone!" + this);
        try {
            Thread.sleep(maxLength *2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        raws.removeAll(raws);
        spinner.setValue(raws.size());
        mg.setCarActivity(lbl, 1);
        System.out.println("Truck has returned!" + this);
        isReady = true;
//        notify();
    }

    public synchronized void pushItem(IWooden stem) {
    	spinner.setValue(raws.size());
        if (raws.size() == maxLength-1) {
            raws.add(stem);
            spinner.setValue(raws.size());
            isReady = false;
            unLoad();
            return;
        }
        raws.add(stem);
        spinner.setValue(raws.size());
    }

    @Override
    public String toString() {
        return "Truck{" +
                "raws=" + raws +
                '}';
    }
}
