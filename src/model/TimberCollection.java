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
		System.out.println("Filling the garage..." + stem);
		while(garage.getReadyIndex()==-1)
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		toString();
		
		garage.fillGarage(stem);
		Thread th = new Thread(() -> {
			mg.drawAnimation(lbl, garage.getReadyIndex() == 1 ? mg.lblCar2 : mg.lblCar1,true);popItem();});
		th.start();
		notify();
	}

	@Override
	public String toString() {
		return "TimberCollection{" +
				" raws=" + raws +
				'}';
	}

}
