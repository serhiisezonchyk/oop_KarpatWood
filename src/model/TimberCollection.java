package model;

import GUI.mainGui;

import javax.swing.*;

public class TimberCollection extends AbstractCollection{
    Garage garage;
    public TimberCollection(short maxLength, Garage garage, JLabel lbl, mainGui mg,JSlider slider) {
        super(maxLength, lbl, mg,slider);
        this.garage = garage;
    }

    public synchronized void pushItem(IWooden stem) {
        System.out.println("Filling the garage..." + stem);
        mg.drawAnimation(lbl, mg.lblCar2);
        garage.fillGarage(stem);
    }

    @Override
    public String toString() {
        return "TimberCollection{" +
                " raws=" + raws +
                '}';
    }
}
