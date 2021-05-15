package model;

import GUI.mainGui;

import java.util.Random;

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
	public synchronized void run(){
		while (!Thread.interrupted()) {
			try {
				Random rnd = new Random();
				long timeOfWork = this.timeOfWork + (long)rnd.nextInt((int)this.timeOfWork)/2;
				mg.setActivityMachine(lbl, true);
				rc.setReady(id);
				Stem poped = (Stem)rc.popItem();
				System.out.println("Poped!" + poped);

				Timber timber = poped.convertToTimber();
				mg.setActivityMachine(lbl, false);
				Thread.sleep(timeOfWork);
				
				poped = null;
				System.out.println("Timber produced = " + timber);
				
				Thread th = new Thread(()->{     		
					while (tc.raws.size() == tc.maxLength) {
						try {
							wait();
						} catch (InterruptedException e) {
						}
					}
					mg.drawAnimation(lbl, mg.lblExportbox,true);});
				th.start();
				Thread.sleep(1000);
				tc.raws.add(timber);
				Thread.sleep(1000);
				toString();
				tc.setSliderValue();
				transport(timber);
				System.out.println("Transported!" + timber);
				rc.setUnReady(id);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
