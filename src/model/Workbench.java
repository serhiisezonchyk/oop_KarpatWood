package model;

import GUI.mainGui;

import javax.swing.*;

public class Workbench extends AbstractWorker{
    TimberCollection tc;
    int id;
    int timeOfWork;

    public Workbench(int timeOfWork,RawCollection rc, TimberCollection tc, JLabel lbl, mainGui mg, int id) {
        super(timeOfWork,rc, lbl, mg);
        this.tc = tc;
        System.out.println(this.tc);
        this.id = id;
        this.timeOfWork = timeOfWork;
        rc.setReady(id);
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
                rc.setReady(id);
                mg.setActivityMachine(lbl, false);
                Thread.sleep(timeOfWork);
                Stem poped = (Stem)rc.popItem();
                System.out.println("Poped!" + poped);
//                Thread.sleep(timeOfWork);
                Timber timber = poped.convertToTimber();
                poped = null;
                System.out.println("Timber produced = " + timber);
                mg.setActivityMachine(lbl, true);
                transport(timber);
                mg.drawAnimation(lbl, mg.lblExportbox);

                System.out.println("Transported!" + timber);
                rc.setUnReady(id);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
