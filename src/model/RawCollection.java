package model;

import GUI.mainGui;

import javax.swing.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

		setSliderValue();
		while (raws.size() == maxLength) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}

		slider.setValue(raws.size());
		raws.add(stem);
		System.out.println(ready);
		while (ready.size() == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		mg.drawAnimation(lbl,ready.get(0) == 0 ? mg.lblMachine1 : mg.lblMachine2,false);
		mg.rawbox_count(getSize());

		setSliderValue();
		
		notify();
	}

	@Override
	public String toString() {
		return "RawCollection{" +
				"raws=" + raws +
				'}';
	}


}
