package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.Random;

public abstract class AbstractWorker implements Runnable {
    long time;
    RawCollection rc;
    JLabel lbl;
    mainGui mg;

    public AbstractWorker(int time ,RawCollection rc, JLabel lbl, mainGui mg) {
        this.rc = rc;
        this.lbl = lbl;
        this.mg = mg;
        this.time = time;
    }


    protected abstract void transport(AbstractWood aw);


    @Override
    public abstract void run();
}
