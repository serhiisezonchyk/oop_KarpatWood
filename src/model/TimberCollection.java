package model;

import GUI.mainGui;

import java.awt.Color;

import javax.swing.*;

public class TimberCollection extends AbstractCollection{
    Garage garage;
    public TimberCollection(short maxLength, Garage garage, JLabel lbl, mainGui mg,JSlider slider) {
        super(maxLength, lbl, mg,slider);
        this.garage = garage;
    }

    public synchronized void pushItem(IWooden stem) {
        while (raws.size() == maxLength) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        raws.add(stem);
        toString();
    	setSliderValue();
        System.out.println("Filling the garage..." + stem);
        Thread th = new Thread(() -> {mg.drawAnimation(lbl, garage.getReadyIndex() == 1 ? mg.lblCar2 : mg.lblCar1,true);});
        th.start();
        popItem();
        toString();
        garage.fillGarage(stem);

    }

    @Override
    public String toString() {
        return "TimberCollection{" +
                " raws=" + raws +
                '}';
    }
    
}
