package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCollection {
    protected short maxLength;
    protected List raws;
    JLabel lbl;
    mainGui mg;

    public AbstractCollection(short maxLength, JLabel lbl, mainGui mg) {
        this.maxLength = maxLength;raws = new CopyOnWriteArrayList();
        this.lbl = lbl;
        this.mg = mg;
    }

    public synchronized void pushItem(IWooden stem) {
        while (raws.size() == maxLength) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        raws.add(stem);
        mg.drawAnimation(lbl,mg.lblMachine1);
        notify();
    }

    public int getLength() {
        return raws.size();
    }

    public synchronized AbstractWood popItem() {
        while (raws.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        AbstractWood item = (AbstractWood)raws.get(raws.size() - 1);
        raws.remove(raws.size() - 1);
        notify();
        return item;
    }
}
