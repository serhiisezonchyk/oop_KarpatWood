package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RawCollection extends AbstractCollection {

    List<Integer> ready = new ArrayList<>(2);

    public RawCollection(short maxLength, JLabel lbl, mainGui mg, JSlider slider) {
        super(maxLength, lbl, mg,slider);
    }

    public void setReady(int i){
        ready.add(i);
    }


    public void setUnReady(int i){
        ready.removeAll(Collections.singleton(i));
    }


    public synchronized void pushItem(IWooden stem) {
        while (raws.size() == maxLength) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        raws.add(stem);
        System.out.println(ready);
        while (ready.size() == 0){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        mg.drawAnimation(lbl,ready.get(0) == 0 ? mg.lblMachine1 : mg.lblMachine2);
        slider.setValue(raws.size());
        notify();
    }

    @Override
    public String toString() {
        return "RawCollection{" +
                "raws=" + raws +
                '}';
    }

}
