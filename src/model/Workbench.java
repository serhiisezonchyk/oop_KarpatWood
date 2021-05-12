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
            	mg.setActivityMachine(lbl, true);
                rc.setReady(id);
                Stem poped = (Stem)rc.popItem();
                /*try {
                    new Thread(() -> {mg.drawAnimation(mg.lblRaw,lbl, true);}).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                System.out.println("Poped!" + poped);
 
                Timber timber = poped.convertToTimber();
                Thread.sleep(timeOfWork);
                mg.setActivityMachine(lbl, false);
                poped = null;
                System.out.println("Timber produced = " + timber);
                Thread th = new Thread(()->{mg.drawAnimation(lbl, mg.lblExportbox,true);});
                th.start();
                
                this.wait(2000);
                
                transport(timber);
                System.out.println("Transported!" + timber);
                rc.setUnReady(id);
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
