package model;

import GUI.mainGui;

import javax.swing.*;

public class Workbench extends AbstractWorker{
    TimberCollection tc;

    public Workbench(int timeOfWork,RawCollection rc, TimberCollection tc, JLabel lbl, mainGui mg) {
        super(1000,rc, lbl, mg);
        this.tc = tc;
        System.out.println(this.tc);
    }

    @Override
    protected void transport(AbstractWood aw) {
        System.out.println(tc);

        aw.makeTransported();
        tc.pushItem(aw);
    }

    @Override
    public synchronized void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(time);
                Stem poped = (Stem)rc.popItem();
                System.out.println("Poped!" + poped);
                Thread.sleep(time);
                Timber timber = poped.convertToTimber();
                poped = null;
                System.out.println("Timber produced = " + timber);
                transport(timber);
                //Thread.sleep(time);
                mg.drawAnimation(lbl, mg.lblExportbox);

                System.out.println("Transported!" + timber);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
