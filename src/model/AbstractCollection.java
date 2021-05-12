package model;

import GUI.mainGui;

import javax.swing.*;

import java.awt.Color;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class AbstractCollection {
    protected short maxLength;
    protected List raws;
    JLabel lbl;
    mainGui mg;
    JSlider slider;
    public AbstractCollection(short maxLength, JLabel lbl, mainGui mg,JSlider slider) {
        this.maxLength = maxLength;
        raws = new CopyOnWriteArrayList();
        this.lbl = lbl;
        this.mg = mg;
        this.slider = slider;
    }

    public abstract void pushItem(IWooden stem);
    
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
        setSliderValue();
        notify();
        return item;
    }
    
    public void setSliderValue() {
		if(slider.getMaximum()*0.9 <= raws.size()){
			slider.setBackground(Color.RED);
		}else if(slider.getMaximum()*0.5 <= raws.size()&&slider.getMaximum()*0.9 >= raws.size()) {
			slider.setBackground(Color.YELLOW);
		}else 
			slider.setBackground(Color.GREEN);
		slider.setValue(raws.size());
    }
    
	public int getSize() {
		return raws.size();
	}

}
