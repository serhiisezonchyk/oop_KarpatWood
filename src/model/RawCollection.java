package model;

import GUI.mainGui;

import javax.swing.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RawCollection extends AbstractCollection {

	public List<Integer> ready = new ArrayList<>(2);

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
		setSliderValue();
		mg.rawbox_count(getSize());
		
		
		notify();
	}


	public synchronized AbstractWood popItem() {
		while (raws.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		System.out.println(ready);
		while (ready.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		Thread th = new Thread(() -> {mg.drawAnimation(lbl,ready.get(0) == 0 ? mg.lblMachine1 : mg.lblMachine2,false);setUnReady(ready.get(0));});
		th.start();
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		AbstractWood item = (AbstractWood)raws.get(raws.size() - 1);
		raws.remove(raws.size() - 1);
		setSliderValue();
		notify();
		return item;
	}

	@Override
	public String toString() {
		return "RawCollection{" +
				"raws=" + raws +
				'}';
	}


}
