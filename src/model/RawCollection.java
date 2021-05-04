package model;

import GUI.mainGui;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RawCollection extends AbstractCollection {

    public RawCollection(short maxLength, JLabel lbl, mainGui mg, JSlider slider) {
        super(maxLength, lbl, mg,slider);
    }

    @Override
    public String toString() {
        return "RawCollection{" +
                "raws=" + raws +
                '}';
    }

}
